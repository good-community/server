package com.bupt.controller;


import com.bupt.model.vo.Response;
import com.bupt.service.WokeyiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wokeyi")
public class WokeyiController {

    @Autowired
    WokeyiService wokeyiService;

    @PostMapping("/query")//显示同社区的劳您驾信息
    public Response query(){

    }

    @PostMapping("/publish")//提交新的响应信息
    public Response publish(){

    }

    @PostMapping("/modify")//修改自己的已提交未接受响应信息
    public Response modify(){

    }

    @PostMapping("/delete")//删除已提交未接受响应信息
    public Response delete(){

    }

}
