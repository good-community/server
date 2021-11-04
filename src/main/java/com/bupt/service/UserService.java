package com.bupt.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bupt.constant.UserRoleEnum;
import com.bupt.model.dto.UserAuthDTO;
import com.bupt.model.dto.UserInfoDTO;
import com.bupt.model.po.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bupt.model.vo.UserInfoVO;

import java.util.List;

/**
 *
 */
public interface UserService extends IService<User> {
    int PSW_MIN_LEN=3;
    User getByUserName(String username);
    void register(UserAuthDTO dto);
    UserInfoVO getInfo(Long userId);
    void updateInfo(UserInfoDTO dto);
    UserRoleEnum getRoleByName(String username);
    Page<UserInfoVO> pageAllInfo();
    UserInfoVO loginUser();
    Long loginId();
    boolean isLoginUser(Long userId);

}
