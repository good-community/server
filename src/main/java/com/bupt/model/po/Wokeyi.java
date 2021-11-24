package com.bupt.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@TableName(value = "wokeyi")
@Data
public class Wokeyi implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /* 响应标识 */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /*请求标识*/
    @TableField(value = "request_id")
    private Long requestId;

    /*响应用户标识*/
    @TableField(value = "user_id")
    private Long userId;

    /*响应描述*/
    @TableField(value= "content")
    private String content;


    /*创建时间*/
    @TableField(value = "begin_date")
    private String beginDate;

    /*修改时间*/
    @TableField(value = "modify_date")
    private String modifyDate;

    /*状态0：待接受；1：同意；2：拒绝；3：取消*/
    @TableField(value = "status")
    private String status;

}
