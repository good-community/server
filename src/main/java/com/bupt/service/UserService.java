package com.bupt.service;

import com.bupt.model.dto.UserAuthDTO;
import com.bupt.model.po.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 */
public interface UserService extends IService<User> {
    User getByUserName(String username);
    void register(UserAuthDTO dto);
}
