package com.rain.shiro.project.controller;

import com.rain.shiro.commons.core.base.BaseController;
import com.rain.shiro.commons.core.result.Result;
import com.rain.shiro.project.entity.SysUser;
import com.rain.shiro.project.entity.po.LoginParam;
import com.rain.shiro.project.service.impl.SysLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "登录接口")
@Slf4j
@RestController
public class SysLoginController extends BaseController {


    @Autowired
    private SysLoginService sysLoginService;

    @ApiOperation(value = "用户登录", notes = "")
    @PostMapping("/login")
    public Result login(@Validated @RequestBody LoginParam loginParam) {
        // 用户信息验证
        SysUser sysUser = sysLoginService.login(loginParam.getUsername(), loginParam.getPassword());
        if(sysUser.getUserName().equals(loginParam.getUsername()) && sysUser.getPassword().equals(loginParam.getPassword())){
            return Result.success("登录成功", sysUser);
        }
        return Result.error("用户名和密码不正确");
    }

    @ApiOperation(value = "用户信息", notes = "用户信息")
    @GetMapping("/info")
    public Result info() {
        Map<String, Object> map = new HashMap<>();
        map.put("roles", new ArrayList<>());
        map.put("introduction", "I am a super admin HaHaHaHa~");
        map.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        map.put("name", "rainbow.pig");
        return success(map);
    }

    @ApiOperation(value = "用户退出", notes = "用户退出")
    @PostMapping("/logout")
    public Result logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return success();
    }
}
