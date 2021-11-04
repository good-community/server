package com.bupt.controller;


import com.bupt.model.po.Wokeyi;
import com.bupt.model.vo.Response;
import com.bupt.service.WokeyiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wokeyi")
public class WokeyiController {

    @Autowired
    WokeyiService wokeyiService;


    @PostMapping("/publish")//提交新的响应信息
    public Response publish(@RequestBody Wokeyi entity){

        wokeyiService.saveOrUpdate(entity);
        return Response.ok();

    }

    @PostMapping("/modify")//修改自己的已提交未接受响应信息
    public Response modify(@RequestBody Wokeyi entity){

        wokeyiService.saveOrUpdate(entity);
        return Response.ok();

    }

    @PostMapping("/delete")//删除已提交未接受响应信息
    public Response delete(@RequestBody Wokeyi entity){

        wokeyiService.removeById(entity);
        return Response.ok();

    }

}
