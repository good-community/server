package com.bupt.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bupt.config.ShiroConfig;
import com.bupt.constant.UserRoleEnum;
import com.bupt.mapper.UserMapper;
import com.bupt.model.dto.UserAuthDTO;
import com.bupt.model.dto.UserInfoDTO;
import com.bupt.model.po.User;
import com.bupt.model.vo.UserInfoVO;
import com.bupt.service.UserService;
import com.bupt.utils.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.stereotype.Service;

import java.io.Serializable;

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

    @Override
    public void register(UserAuthDTO dto) {
        String username = dto.getUsername();
        String password = dto.getPassword();
        User user = this.getByUserName(username);
        if (user != null) {
            throw new RuntimeException("用户名已存在");
        }

        //将用户名作为加密盐值
        user = new User();
        user.setUsername(username);
        String hashedPassword = this.getHashedPassword(username, password);
        user.setPassword(hashedPassword);
        super.save(user);
        log.info("user register success, {}", dto);
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
    public void updateInfo(UserInfoDTO dto) {
        Long id = dto.getUserId();
        User user = this.getById(id);
        if (user == null) {
            throw new RuntimeException("user not exist, id = " + id);
        }

        //密码
        String password = dto.getPassword();
        if (password != null) {
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




