package com.bupt.model.vo;

import com.bupt.constant.CommunityType;
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
     * 身份证号
     */
    private String idNo;

    /**
     * 注册城市
     */
    private String city;

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
     * 注册社区类型
     */
    private String communityType;

    /**
     * PO转VO
     *
     * @param user UserPO
     */
    public UserInfoVO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.idNo = user.getIdNo();
        this.city = user.getCity();
        this.contact = user.getContact();
        this.introduction = user.getIntroduction();
        this.avatarUrl = user.getAvatarUrl();
        this.communityType = CommunityType.getName(user.getCommunityType());
    }
}
