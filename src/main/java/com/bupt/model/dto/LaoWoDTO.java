package com.bupt.model.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.bupt.model.po.Laoninjia;

public class LaoWoDTO {

    /* 请求标识 */
    private Long id;

    private Long userId;

    /*请求类型 hour,heavy,ride,volunteer四种*/
    private String kind;

    /*主题名称*/
    private String subject;


    /*请求描述*/
    private String content;

    /*请求人数*/
    private String numbers;

    /*请求结束日期*/
    private String endDate;

    /*请求介绍照片*/
    private String  base64Image;

    /*创建时间*/
    private String beginDate;

    /*修改时间*/
    private String modifyDate;

    /*状态  已完成、待响应、已取消、到期未达成*/
    private String status;

    private Long responseId;

    private String responseContent;

    private String responseStatus;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNumbers() {
        return numbers;
    }

    public void setNumbers(String numbers) {
        this.numbers = numbers;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getResponseId() {
        return responseId;
    }

    public void setResponseId(Long responseId) {
        this.responseId = responseId;
    }

    public String getResponseContent() {
        return responseContent;
    }

    public void setResponseContent(String responseContent) {
        this.responseContent = responseContent;
    }

    public String getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(String responseStatus) {
        this.responseStatus = responseStatus;
    }

    public LaoWoDTO(Laoninjia a){

        this.id=a.getId();
        this.base64Image=a.getBase64Image();
        this.beginDate=a.getBeginDate();
        this.content=a.getContent();
        this.endDate=a.getEndDate();
        this.kind=a.getKind();
        this.modifyDate=a.getModifyDate();
        this.numbers=a.getNumbers();
        this.status=a.getStatus();
        this.subject=a.getSubject();
        this.userId=a.getUserId();

    }


}
