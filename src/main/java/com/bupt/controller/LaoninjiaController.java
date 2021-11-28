package com.bupt.controller;


import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bupt.model.po.Laoninjia;
import com.bupt.model.vo.Response;
import com.bupt.service.LaoninjiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/laoninjia")
public class LaoninjiaController {

    @Autowired
    LaoninjiaService laoninjia;

    @PostMapping("/query_all")//显示所有请求帮忙信息
    public Response query_all(){

        List<Laoninjia> a=laoninjia.list();
        return Response.ok(a);

    }

    @PostMapping("/query_user")//某用户请求信息
    public Response query_user(@RequestParam("userId") String id){

        QueryWrapper<Laoninjia> tmp=new QueryWrapper<>();
        tmp.eq("user_id",id);

        return Response.ok(laoninjia.list(tmp));

    }


    @PostMapping("/search_subject")    //主题名称模糊搜索
    public Response search(String content)
    {

        QueryWrapper<Laoninjia> tmp=new QueryWrapper<>();
        tmp.like("subject",content);
        return Response.ok(laoninjia.list(tmp));
    }


    @PostMapping("/search_kind")    //帮忙类型搜索
    public Response search2(String content)
    {

        QueryWrapper<Laoninjia> tmp=new QueryWrapper<>();
        tmp.like("kind",content);
        return Response.ok(laoninjia.list(tmp));
    }



    @PostMapping("/publish")//发布新信息
    public Response publish(@RequestBody Laoninjia entity){

        laoninjia.saveOrUpdate(entity);
        return Response.ok();

    }

    @PostMapping("/modify")//修改
    public Response modify(@RequestBody Laoninjia entity){

        laoninjia.saveOrUpdate(entity);
        return Response.ok();

    }

    @PostMapping("/delete")//删除
    public Response delete(@RequestBody Laoninjia entity){

        laoninjia.removeById(entity.getId());
        return Response.ok();

    }













}
