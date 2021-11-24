package com.bupt.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bupt.config.ShiroConfig;
import com.bupt.constant.UserRoleEnum;
import com.bupt.mapper.UserMapper;
import com.bupt.model.dto.UserInfoDTO;
import com.bupt.model.dto.UserRegisterDTO;
import com.bupt.model.po.User;
import com.bupt.model.vo.UserInfoVO;
import com.bupt.service.UserService;
import com.bupt.utils.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Calendar;

/**
 *
 */
@Service
@Slf4j

public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {
    @Override
    public User getById(Serializable id) {
        return lambdaQuery().eq(User::getId, id).one();
    }

    /**
     * 仅查找status为1的
     */
    @Override
    public LambdaQueryChainWrapper<User> lambdaQuery() {
        return super.lambdaQuery()
                .eq(User::getStatus, 1);
    }


    @Override
    public User getByUserName(String username) {
        return this.lambdaQuery()
                .eq(User::getUsername, username)
                .one();
    }

    /**
     * 若最近一个月内该身份证号被注册过其他城市，则返回true
     */
    private boolean isIdNoRepeat(UserRegisterDTO dto) {
        Calendar rightNow = Calendar.getInstance();
        rightNow.add(Calendar.MONTH, -1);
        return this.lambdaQuery()
                .eq(User::getIdNo, dto.getIdNo())
                .ne(User::getCity, dto.getCity())
                .gt(User::getCreateTime, rightNow.getTime())
                .exists();

    }

    @Override
    public void register(UserRegisterDTO dto) {
        String username = dto.getUsername();
        String password = dto.getPassword();
        String idNo = dto.getIdNo();
        Integer city = dto.getCity();
        Integer communityType = dto.getCommunityType();

        //校验是否可以注册
        checkRegister(dto);
        //保存
        User user = new User();
        user.setUsername(username);
        user.setCity(city);
        user.setIdNo(idNo);
        user.setCommunityType(communityType);
        //将用户名作为加密盐值
        String hashedPassword = this.getHashedPassword(username, password);
        user.setPassword(hashedPassword);

        super.save(user);
        log.info("user register success, {}", dto);
    }

    private void checkRegister(UserRegisterDTO dto) {
        //用户名判重
        User user = this.getByUserName(dto.getUsername());
        if (user != null) {
            throw new RuntimeException("用户名已存在");
        }

        //身份证号判重
        if(isIdNoRepeat(dto)){
            throw new RuntimeException("该身份证号在一个月内被注册过其他城市");
        }
    }

    @Override
    public UserInfoVO getInfo(Long userId) {
        User user = lambdaQuery().eq(User::getId, userId)
                .one();
        if (user == null) {
            throw new RuntimeException("user not exist, id = " + userId);
        }
        return new UserInfoVO(user);
    }
    @Override
    public UserInfoVO getInfo(String username) {
        User user = lambdaQuery().eq(User::getUsername, username)
                .one();
        if (user == null) {
            throw new RuntimeException("user not exist, username = " + username);
        }
        return new UserInfoVO(user);
    }

    @Override
    public void updateInfo(UserInfoDTO dto) {
        Long id = dto.getUserId();
        User user = this.getById(id);
        if (user == null) {
            throw new RuntimeException("user not exist, id = " + id);
        }

        //密码
        String password = dto.getPassword();
        if (StringUtils.isNotBlank(password)) {
            if (password.length() < PSW_MIN_LEN) {
                throw new RuntimeException("密码长度过短：" + password.length());
            }
            String hashedPassword = this.getHashedPassword(user.getUsername(), password);
            user.setPassword(hashedPassword);
        }

        //联系方式
        user.setContact(dto.getContact());

        //个人简介
        user.setIntroduction(dto.getIntroduction());

        //头像url
        user.setAvatarUrl(dto.getAvatarUrl());

        super.updateById(user);
    }

    @Override
    public UserRoleEnum getRoleByName(String username) {
        User user = this.getByUserName(username);
        return UserRoleEnum.getByCode(user.getRole());
    }

    @Override
    public Page<UserInfoVO> pageAllInfo() {
        //查DB
        Page<User> page = lambdaQuery().orderByDesc(User::getCreateTime)
                .page(new Page<>(1, 10));

        //转VO
        return PageUtils.convert(page, UserInfoVO::new);
    }

    private String getHashedPassword(String username, String oriPassword) {
        return new SimpleHash(
                ShiroConfig.ALGORITHM,
                oriPassword,
                username,
                ShiroConfig.ITERATES).toString();
    }

    @Override
    public UserInfoVO loginUser() {
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        User user = this.getByUserName(username);
        return new UserInfoVO(user);
    }

    @Override
    public Long loginId() {
        return this.loginUser().getId();
    }

    @Override
    public boolean isLoginUser(Long userId) {
        if (userId == null) {
            return false;
        }
        return userId.equals(this.loginId());
    }
}




