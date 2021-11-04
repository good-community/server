package com.bupt.controller;


import com.bupt.model.vo.Response;
import com.bupt.service.LaoninjiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/laoninjia")
public class LaoninjiaController {

    @Autowired
    LaoninjiaService laoninjia;

    @PostMapping("/query_all")//显示所有请求帮忙信息
    public Response query_all(){



    }

    @PostMapping("/query_user")//某用户请求信息
    public Response query_user(){

    }

    @PostMapping("/publish")//发布新信息
    public Response publish(){

    }

    @PostMapping("/modify")//修改
    public Response modify(){

    }

    @PostMapping("/delete")//删除
    public Response delete(){

    }













}
