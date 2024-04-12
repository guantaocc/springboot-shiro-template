package com.rain.shiro.framework.shiro;

import com.rain.shiro.commons.exception.user.UserPasswordRetryLimitExceedException;
import com.rain.shiro.commons.utils.ShiroUtils;
import com.rain.shiro.project.entity.SysUser;
import com.rain.shiro.project.service.impl.SysLoginService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 自定义权限匹配和账号密码匹配
 */
@Slf4j
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private SysLoginService loginService;

    /**
     * 鉴权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SysUser sysUser = ShiroUtils.getSysUser();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 管理员默认拥有所有权限
        if (sysUser.isAdmin()) {
            info.addRole("admin");
            info.addStringPermission("*:*:*");
        }
        return info;
    }

    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername();
        String password = new String(upToken.getPassword());
        SysUser sysUser = null;
        try {
            sysUser = loginService.login(username, password);
        } catch (UserPasswordRetryLimitExceedException e) {
            throw new ExcessiveAttemptsException(e.getMessage(), e);
        } catch (Exception e) {
            throw new AuthenticationException(e.getMessage(), e);
        }
        return new SimpleAuthenticationInfo(sysUser, password, getName());
    }
}