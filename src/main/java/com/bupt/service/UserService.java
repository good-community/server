package com.bupt.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bupt.constant.UserRoleEnum;
import com.bupt.model.dto.UserLoginDTO;
import com.bupt.model.dto.UserInfoDTO;
import com.bupt.model.dto.UserRegisterDTO;
import com.bupt.model.po.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bupt.model.vo.UserInfoVO;

/**
 *
 */
public interface UserService extends IService<User> {
    int PSW_MIN_LEN=3;
    User getByUserName(String username);
    void register(UserRegisterDTO dto);
    UserInfoVO getInfo(Long userId);
    void updateInfo(UserInfoDTO dto);
    UserRoleEnum getRoleByName(String username);
    Page<UserInfoVO> pageAllInfo();
    UserInfoVO loginUser();
    Long loginId();
    boolean isLoginUser(Long userId);

}
