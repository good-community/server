package com.bupt.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户表
 *
 * @TableName user
 */
@TableName(value = "user")
@Data
public class User implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 用户名
     */
    @TableField(value = "username")
    private String username;
    /**
     * 密码（加密）
     */
    @TableField(value = "password")
    private String password;
    /**
     * 联系方式
     */
    @TableField(value = "contact")
    private String contact;
    /**
     * 个人简介
     */
    @TableField(value = "introduction")
    private String introduction;
    /**
     * 用户头像url
     */
    @TableField(value = "avatar_url")
    private String avatarUrl;

    /**
     * 角色
     */
    @TableField("role")
    private Integer role;

    /**
     * 状态：1正常，0注销
     */
    @TableField(value = "status")
    private Long status;
    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;
    /**
     * 创建时间
     */
    @TableField(value = "update_time")
    private Date updateTime;
}