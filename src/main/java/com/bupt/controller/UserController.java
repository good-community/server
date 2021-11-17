package com.bupt.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bupt.constant.RetCodeEnum;
import com.bupt.model.dto.UserLoginDTO;
import com.bupt.model.dto.UserInfoDTO;
import com.bupt.model.dto.UserRegisterDTO;
import com.bupt.model.vo.Response;
import com.bupt.model.vo.UserInfoVO;
import com.bupt.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresRoles;
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
    public Response login(@RequestBody @Valid UserLoginDTO dto) {
        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        // 在认证提交前准备 token（令牌）
        UsernamePasswordToken token = new UsernamePasswordToken(dto.getUsername(), dto.getPassword());
        // 执行认证登陆
        try {
            subject.login(token);
        } catch (IncorrectCredentialsException e) {
            return Response.fail("用户名或密码错误");
        }
        UserInfoVO vo = userService.getInfo(dto.getUsername());
        return Response.ok(vo);
    }

    @PostMapping("/register")
    public Response register(@RequestBody @Valid UserRegisterDTO dto) {
        userService.register(dto);
        return Response.ok();
    }

    /**
     * 前端重定向到登录页
     */
    @GetMapping("/notLogin")
    public Response loginRedirect() {
        return Response.any(RetCodeEnum.NOT_LOGIN);
    }

    @GetMapping("/info/{userId}")
    public Response getUserInfo(@PathVariable Long userId) {
        UserInfoVO vo = userService.getInfo(userId);
        return Response.ok(vo);
    }

    @PutMapping("/info")
    public Response updateUserInfo(@RequestBody UserInfoDTO dto) {
        if(!userService.isLoginUser(dto.getUserId())){
            throw new UnauthorizedException();
        }
        userService.updateInfo(dto);
        return Response.ok();
    }

    @RequiresRoles("ROLE_ADMIN")
    @GetMapping("/info/all")
    public Response getAllUserInfo(){
        Page<UserInfoVO> allUserInfo = userService.pageAllInfo();
        return Response.ok(allUserInfo);
    }
}
