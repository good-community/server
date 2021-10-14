package com.bupt.controller;

import com.bupt.constant.RetCodeEnum;
import com.bupt.model.dto.UserAuthDTO;
import com.bupt.model.vo.Response;
import com.bupt.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Response login(@RequestBody @Valid UserAuthDTO dto){
        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        // 在认证提交前准备 token（令牌）
        UsernamePasswordToken token = new UsernamePasswordToken(dto.getUsername(), dto.getPassword());
        // 执行认证登陆
        try {
            subject.login(token);
        }catch (IncorrectCredentialsException e){
            return Response.fail("用户名或密码错误");
        }
        return Response.ok();
    }

    @PostMapping("/register")
    public Response register(@RequestBody @Valid UserAuthDTO dto){
        userService.register(dto);
        return Response.ok();
    }

    /**
     * 前端重定向到登录页
     */
    @GetMapping("/notLogin")
    public Response loginRedirect(){
        return Response.any(RetCodeEnum.NOT_LOGIN);
    }
}
