package com.msg.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import com.msg.domain.Admin;

@Component
public class MyShiroRealm extends AuthorizingRealm {

    public static final Map<String, String> shiroMap = new HashMap<String, String>();

    static {
        shiroMap.put("/", "anon");
        shiroMap.put("/login", "anon");
        shiroMap.put("/res/**", "anon");
        shiroMap.put("/api/**", "anon");
        shiroMap.put("/**", "authc");
    }

    @Resource
    AdminService adminService;

    @Resource
    private SecurityService securityService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principals) {
        return new SimpleAuthorizationInfo();
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        Admin user = adminService.getAdminByUsername(token.getUsername());
        if (null == user) {
            throw new UnknownAccountException();
        }
        return new SimpleAuthenticationInfo(user.getUsername(), new String(
                user.getPassword()), user.getUsername());
    }

}
