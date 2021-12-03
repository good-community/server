package com.bupt.controller;


import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bupt.model.po.Laoninjia;
import com.bupt.model.vo.Response;
import com.bupt.service.LaoninjiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/laoninjia")
public class LaoninjiaController {

    @Autowired
    LaoninjiaService laoninjia;

    @PostMapping("/query_all")//显示所有请求帮忙信息
    public Response query_all() throws Exception{

        List<Laoninjia> list=laoninjia.list();
        Date date=new Date();
        SimpleDateFormat format=new SimpleDateFormat("yyyy/MM/dd");
        String now=format.format(date);

        for(int i=0;i<list.size();i++)
        {

            Laoninjia cur=list.get(i);
            if(format.parse(cur.getEndDate()).compareTo(format.parse(now))<0)
            {
                if(!cur.getStatus().equals("已完成")){cur.setStatus("到期未达成");
                    laoninjia.updateById(cur);}
            }
            if(cur.getStatus().equals("已完成")||cur.getStatus().equals("到期未完成"))list.remove(i);
        }
        return Response.ok(list);

    }

    @PostMapping("/query_user")//某用户请求信息
    public Response query_user(@RequestParam("userId") String id) throws Exception{

        QueryWrapper<Laoninjia> tmp=new QueryWrapper<>();
        tmp.eq("user_id",id);
        List<Laoninjia> list=laoninjia.list(tmp);
        Date date=new Date();
        SimpleDateFormat format=new SimpleDateFormat("yyyy/MM/dd");
        String now=format.format(date);

        for(int i=0;i<list.size();i++)
        {

         Laoninjia cur=list.get(i);
         if(format.parse(cur.getEndDate()).compareTo(format.parse(now))<0)
         {
             if(!cur.getStatus().equals("已完成")){cur.setStatus("到期未达成");
             laoninjia.updateById(cur);}
         }
        }

        return Response.ok(list);

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
