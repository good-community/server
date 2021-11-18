package com.bupt.controller;

import com.bupt.constant.CityEnum;
import com.bupt.constant.CommunityEnum;
import com.bupt.model.vo.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dict")
public class DictController {
    @GetMapping("/city")
    public Response getCityDict() {
        return Response.ok(CityEnum.getVO());
    }

    @GetMapping("/community")
    public Response getCommunityDict() {
        return Response.ok(CommunityEnum.getVO());
    }
}
