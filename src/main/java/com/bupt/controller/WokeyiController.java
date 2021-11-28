package com.bupt.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.bupt.model.dto.LaoWoDTO;
import com.bupt.model.po.Laoninjia;
import com.bupt.model.po.Wokeyi;
import com.bupt.model.vo.Response;
import com.bupt.service.LaoninjiaService;
import com.bupt.service.WokeyiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/wokeyi")
public class WokeyiController {

    @Autowired
    WokeyiService wokeyiService;

    @Autowired
    LaoninjiaService laoninjiaService;


    @PostMapping("/publish")//提交新的响应信息
    public Response publish(@RequestBody Wokeyi entity){
         System.out.println("lalala");
        System.out.println("id:!!!"+entity.getUserId());
        System.out.println("date:!!!"+entity.getBeginDate());
        wokeyiService.saveOrUpdate(entity);
        return Response.ok();

    }

    @PostMapping("/info_user")//返回用户的响应信息
    public Response info1(@RequestParam Long user_id){
        QueryWrapper<Wokeyi> tmp=new QueryWrapper<>();
        tmp.eq("user_id",user_id);
        return Response.ok(wokeyiService.list(tmp));
    }

    @PostMapping("/info_request")//返回劳您驾请求对应的响应信息
    public Response info(@RequestParam("requestId") Long request_id){
        QueryWrapper<Wokeyi> tmp=new QueryWrapper<>();
        tmp.eq("request_id",request_id);
        return Response.ok(wokeyiService.list(tmp));
    }

    @PostMapping("/modify")//修改自己的已提交未接受响应信息
    public Response modify(@RequestBody Wokeyi entity){

        UpdateWrapper<Wokeyi> tmp=new UpdateWrapper<>();
        tmp.eq("id", entity.getId()).set("content",entity.getContent()).set("modify_date",entity.getModifyDate());
        wokeyiService.update(tmp);
        return Response.ok();

    }

    @PostMapping("/delete")//删除已提交未接受响应信息
    public Response delete(@RequestParam("responseId") String id){


        System.out.println("删除响应ID"+id);

        wokeyiService.removeById(id);
        return Response.ok();

    }

    @PostMapping("/request")
    public Response request(@RequestParam("userId") String id){

        QueryWrapper<Wokeyi> tmp=new QueryWrapper<>();
        tmp.eq("user_id",id);
        List<Wokeyi> tmplist=wokeyiService.list(tmp);
        List<LaoWoDTO> ans= new ArrayList<>();

        for(int i=0;i<tmplist.size();i++)
        {
            Wokeyi tmpWo=tmplist.get(i);
            Long a=tmpWo.getRequestId();
            QueryWrapper<Laoninjia> tmp_wrapper=new QueryWrapper<>();
            tmp_wrapper.eq("id",a);
            Laoninjia tmpLao=laoninjiaService.getOne(tmp_wrapper);

            LaoWoDTO tmpans=new LaoWoDTO(tmpLao);
            tmpans.setResponseId(tmpWo.getId());
            tmpans.setResponseContent(tmpWo.getContent());
            tmpans.setResponseStatus(tmpWo.getStatus());
            ans.add(tmpans);
        }

        return Response.ok(ans);


    }

    @PostMapping("/reject")//拒绝响应的逻辑
    public Response reject(@RequestParam("responseId") String id)
    {

        System.out.println(id);

        return Response.ok();

    }

    @PostMapping("/accept")//接受响应的逻辑
    public Response accept(@RequestParam("responseId") String id)

    {
        System.out.println(id);

        return Response.ok();

    }



}
