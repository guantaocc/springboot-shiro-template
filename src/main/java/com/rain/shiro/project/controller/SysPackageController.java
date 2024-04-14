package com.rain.shiro.project.controller;

import java.util.List;

import com.rain.shiro.commons.core.result.Result;
import com.rain.shiro.commons.utils.sign.Md5Utils;
import com.rain.shiro.commons.utils.ShiroUtils;
import com.rain.shiro.project.entity.SysPackage;
import com.rain.shiro.project.service.ISysPackageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import com.rain.shiro.commons.core.base.BaseController;
import com.rain.shiro.commons.utils.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api(tags = "套餐接口")
@RestController
@RequestMapping("/package")
public class SysPackageController extends BaseController {

    @Autowired
    private ISysPackageService sysPackageService;

    @ApiOperation(value = "查询套餐列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", defaultValue = "1", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "页数", defaultValue = "10", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "orderByColumn", value = "排序字段", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "isAsc", value = "asc-升序, desc-降序", dataType = "string", paramType = "query")
    })
    @GetMapping("/list")
    public TableDataInfo list(SysPackage sysPackage) {
        startPage();
        List<SysPackage> list = sysPackageService.selectSysPackageList(sysPackage);
        return getDataTable(list);
    }



    @ApiOperation(value = "获取单个套餐")
    @ApiImplicitParam(name = "id", value = "分类ID", dataType = "int", paramType = "query", required = true)
    @GetMapping(value = "/getInfo")
    public Result getInfo(Long id) {
        return success(sysPackageService.selectSysPackageById(id));
    }

    @ApiOperation(value = "新增套餐")
    @PostMapping("/insert")
    public Result insert(@Validated @RequestBody SysPackage sysPackage) {
        return isSuccess(sysPackageService.insertSysPackage(sysPackage));
    }

    @ApiOperation(value = "修改套餐")
    @PostMapping("/update")
    public Result update(@Validated @RequestBody SysPackage sysPackage) {
        return isSuccess(sysPackageService.updateSysPackage(sysPackage));
    }

    @ApiOperation(value = "批量删除套餐")
    @DeleteMapping("/batchDelete/{ids}")
    public Result batchDelete(@PathVariable Long[] ids) {
        return isSuccess(sysPackageService.deleteSysPackageByIds(ids));
    }
}
