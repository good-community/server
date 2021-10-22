package com.bupt.model.vo;

import com.bupt.model.po.User;
import lombok.Data;

@Data
public class UserInfoVO {
    private Long id;
    /**
     * 用户名
     */
    private String username;
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

    /**
     * PO转VO
     * @param user UserPO
     */
    public UserInfoVO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.contact = user.getContact();
        this.introduction = user.getIntroduction();
        this.avatarUrl = user.getAvatarUrl();
    }
}
