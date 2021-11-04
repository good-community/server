package com.bupt.model.po;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName(value = "record")
@Data
public class record {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /* 请求标识 */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /* 发布用户标识 */
    @TableField(value = "publish_user_id")
    private Long PublishUserId;

    /* 响应用户标识 */
    @TableField(value = "response_user_id")
    private Long ResponseUserId;

    @TableField(value = "date")
    private Date date;

    @TableField(value = "publish_fee")
    private Long PublishFee;

    @TableField(value = "response_fee")
    private Long ResponseFee;


}
