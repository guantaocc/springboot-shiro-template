package com.rain.shiro.project.service.impl;

import com.rain.shiro.project.entity.SysUser;
import com.rain.shiro.project.service.SysUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * 登录校验方法
 */
@Component
public class SysLoginService {

    @Autowired
    private SysUserService sysUserService;
    /**
     * 登录校验
     */
    public SysUser login(String username, String password) {
        SysUser sysUser = sysUserService.selectSysUserByUserName(username);
        if (sysUser == null) {
            throw new AuthenticationException();
        }
        return sysUser;
    }
}
