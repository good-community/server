package com.bupt.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bupt.config.ShiroConfig;
import com.bupt.mapper.UserMapper;
import com.bupt.model.dto.UserAuthDTO;
import com.bupt.model.po.User;
import com.bupt.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {
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
        String hashedPassword = new SimpleHash(
                ShiroConfig.ALGORITHM,
                password,
                username,
                ShiroConfig.ITERATES).toBase64();
        user = new User();
        user.setUsername(username);
        user.setPassword(hashedPassword);
        super.save(user);
        log.info("user register success, {}", dto);
    }
}




