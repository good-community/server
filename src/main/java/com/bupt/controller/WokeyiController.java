package com.bupt.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.bupt.model.dto.LaoWoDTO;
import com.bupt.model.po.Laoninjia;
import com.bupt.model.po.Wokeyi;
import com.bupt.model.po.record;
import com.bupt.model.vo.Response;
import com.bupt.service.LaoninjiaService;
import com.bupt.service.RecordService;
import com.bupt.service.WokeyiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.integration.IntegrationAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

@RestController
@RequestMapping("/wokeyi")
public class WokeyiController {

    @Autowired
    WokeyiService wokeyiService;

    @Autowired
    LaoninjiaService laoninjiaService;

    @Autowired
    RecordService recordService;


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
        List<Wokeyi> list=wokeyiService.list(tmp);
        for(int i=0;i<list.size();i++)
        {

            Wokeyi cur=list.get(i);
            if(cur.getStatus().equals("同意")||cur.getStatus().equals("拒绝"))list.remove(i);
        }

        return Response.ok(list);
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

        QueryWrapper<Wokeyi> wrapper=new QueryWrapper<>();
        wrapper.eq("id",id);
        Wokeyi response=wokeyiService.getOne(wrapper);
        Long requestId=response.getRequestId();
        QueryWrapper<Laoninjia> wrapper0=new QueryWrapper<>();
        wrapper0.eq("id",requestId);
        Laoninjia request= laoninjiaService.getOne(wrapper0);

        response.setStatus("拒绝");
        wokeyiService.updateById(response);

        return Response.ok();

    }

    @PostMapping("/accept")//接受响应的逻辑
    public Response accept(@RequestParam("responseId") String id)

    {

        Date date=new Date();
        QueryWrapper<Wokeyi> wrapper=new QueryWrapper<>();
        wrapper.eq("id",id);
        Wokeyi response=wokeyiService.getOne(wrapper);
        Long requestId=response.getRequestId();
        QueryWrapper<Laoninjia> wrapper0=new QueryWrapper<>();
        wrapper0.eq("id",requestId);
        Laoninjia request= laoninjiaService.getOne(wrapper0);

        response.setStatus("同意");
        request.setStatus("已完成");

        record Record=new record();
        Record.setDate(date);
        Integer num=Integer.parseInt(request.getNumbers());
        Record.setPublishFee(num.longValue()*3);
        Record.setResponseFee(new Long(1));
        Record.setPublishUserId(request.getUserId());
        Record.setResponseUserId(response.getUserId());

        recordService.save(Record);
        laoninjiaService.updateById(request);
        wokeyiService.updateById(response);

        return Response.ok();

    }



}
