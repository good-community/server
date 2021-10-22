package com.bupt.config;

import com.bupt.constant.UserRoleEnum;
import com.bupt.model.po.User;
import com.bupt.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

@Slf4j
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 权限认证
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> roleSet = new HashSet<>();
        UserRoleEnum roleEnum = userService.getRoleByName(username);
        roleSet.add(roleEnum.name);
        info.setRoles(roleSet);
        return info;
    }


    /**
     * 身份认证
     *
     * @param authenticationToken 用户输入的认证信息
     * @return 数据库中的身份信息
     * @throws AuthenticationException 用户名不存在时抛出
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        String password = new String(token.getPassword());
        log.info("user login, username = {}, password = {}", username, password);

        //根据用户名从数据库获取密码
        User user = userService.getByUserName(username);

        if (user == null) {
            throw new AccountException("用户名不存在");
        }
        String realHashedPassword = user.getPassword();

        //拿唯一的用户名作为加密盐值
        ByteSource salt = ByteSource.Util.bytes(username);
        return new SimpleAuthenticationInfo(username, realHashedPassword, salt, getName());
    }
}
