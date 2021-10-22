package com.bupt.model.dto;

import com.bupt.model.po.User;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
public class UserInfoDTO {
    /**
     * id
     */
    @NotNull
    private Long userId;
    /**
     * 密码
     */
    private String password;
    /**
     * 联系方式
     */
    private String contact;
    /**
     * 个人简介
     */
    private String introduction;
    /**
     * 用户头像url
     */
    private String avatarUrl;
}
