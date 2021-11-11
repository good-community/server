package com.bupt.model.po;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.apache.ibatis.type.JdbcType;

import java.io.Serializable;
import java.util.Date;

@TableName(value = "laoninjia")
@Data
public class Laoninjia implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /* 请求标识 */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(value = "user_id")
    private Long userId;

    /*请求类型 hour,heavy,ride,volunteer四种*/
    @TableField(value = "kind")
    private String kind;

    /*主题名称*/
    @TableField(value = "subject")
    private String subject;


    /*请求描述*/
    @TableField(value = "content")
    private String content;

    /*请求人数*/
    @TableField(value = "numbers")
    private String numbers;

    /*请求结束日期*/
    @TableField(value = "end_date")
    private Date EndDate;

    /*请求介绍照片,blob字段存储*/
    @TableField(value = "base64_image")
    private String  Base64Image;

    /*创建时间*/
    @TableField(value = "begin_date")
    private Date BeginDate;

    /*修改时间*/
    @TableField(value = "modify_date")
    private String ModifyDate;

    /*状态 complete wait cancel fail*/
    @TableField(value = "status")
    private String status;



}
