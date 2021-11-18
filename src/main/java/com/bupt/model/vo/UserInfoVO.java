package com.bupt.model.vo;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.bupt.constant.CityEnum;
import com.bupt.constant.CommunityEnum;
import com.bupt.model.po.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * PO转VO
     *
     * @param user UserPO
     */
    public UserInfoVO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.idNo = user.getIdNo();
        this.city = CityEnum.getName(user.getCity());
        this.contact = user.getContact();
        this.introduction = user.getIntroduction();
        this.avatarUrl = user.getAvatarUrl();
        this.communityType = CommunityEnum.getName(user.getCommunityType());
        this.createTime = user.getCreateTime();
        this.updateTime = user.getUpdateTime();
    }
}
