package com.rain.shiro.project.controller;

import java.util.List;

import com.rain.shiro.commons.core.result.Result;
import com.rain.shiro.commons.utils.sign.Md5Utils;
import com.rain.shiro.commons.utils.ShiroUtils;
import com.rain.shiro.project.entity.SysUser;
import com.rain.shiro.project.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import com.rain.shiro.commons.core.base.BaseController;
import com.rain.shiro.commons.utils.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "用户接口")
@RestController
@RequestMapping("/user")
public class SysUserController extends BaseController {

    @Autowired
    private SysUserService sysUserService;

    @ApiOperation(value = "查询用户列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", defaultValue = "1", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "页数", defaultValue = "10", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "orderByColumn", value = "排序字段", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "isAsc", value = "asc-升序, desc-降序", dataType = "string", paramType = "query")
    })
    @GetMapping("/list")
    public TableDataInfo list(SysUser sysUser) {
        startPage();
        List<SysUser> list = sysUserService.selectSysUserList(sysUser);
        return getDataTable(list);
    }

    @ApiOperation(value = "获取单个用户")
    @ApiImplicitParam(name = "userId", value = "用户ID", dataType = "int", paramType = "query", required = true)
    @GetMapping(value = "/getInfo")
    public Result getInfo(Long userId) {
        return success(sysUserService.selectSysUserByUserId(userId));
    }

    @ApiOperation(value = "新增用户")
    @PostMapping("/insert")
    public Result insert(@Validated @RequestBody SysUser sysUser) {
        if (!sysUserService.checkUserNameUnique(sysUser)) {
            return error("新增用户'" + sysUser.getUserName() + "'失败，用户账号已存在");
        }
        sysUser.setSalt(ShiroUtils.randomSalt());
        String password = Md5Utils.encryptPassword(sysUser.getUserName(), sysUser.getPassword(), sysUser.getSalt());
        sysUser.setPassword(password);
        return isSuccess(sysUserService.insertSysUser(sysUser));
    }

    @ApiOperation(value = "修改用户")
    @PostMapping("/update")
    public Result update(@Validated @RequestBody SysUser sysUser) {
        return isSuccess(sysUserService.updateSysUser(sysUser));
    }

    @ApiOperation(value = "批量删除用户")
    @PostMapping("/batchDelete")
    public Result batchDelete(@PathVariable Long[] userIds) {
        return isSuccess(sysUserService.deleteSysUserByUserIds(userIds));
    }
}
