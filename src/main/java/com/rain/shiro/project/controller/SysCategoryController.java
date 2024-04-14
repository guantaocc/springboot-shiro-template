package com.rain.shiro.project.controller;

import java.util.List;

import com.rain.shiro.commons.core.result.Result;
import com.rain.shiro.commons.utils.sign.Md5Utils;
import com.rain.shiro.commons.utils.ShiroUtils;
import com.rain.shiro.project.entity.SysCategory;
import com.rain.shiro.project.entity.SysPackage;
import com.rain.shiro.project.service.SysCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import com.rain.shiro.commons.core.base.BaseController;
import com.rain.shiro.commons.utils.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api(tags = "分类接口")
@RestController
@RequestMapping("/category")
public class SysCategoryController extends BaseController {

    @Autowired
    private SysCategoryService SysCategoryService;

    @ApiOperation(value = "查询分类列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", defaultValue = "1", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "页数", defaultValue = "10", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "orderByColumn", value = "排序字段", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "isAsc", value = "asc-升序, desc-降序", dataType = "string", paramType = "query")
    })
    @GetMapping("/list")
    public TableDataInfo list(SysCategory sysCategory) {
        startPage();
        List<SysCategory> list = SysCategoryService.selectList(sysCategory);
        return getDataTable(list);
    }

    @GetMapping("/all")
    public Result all(SysCategory sysCategory) {
        List<SysCategory> list = SysCategoryService.selectList(sysCategory);
        return success(list);
    }

    @ApiOperation(value = "获取单个分类")
    @ApiImplicitParam(name = "userId", value = "分类ID", dataType = "int", paramType = "query", required = true)
    @GetMapping(value = "/getInfo")
    public Result getInfo(Long userId) {
        return success(SysCategoryService.selectById(userId));
    }

    @ApiOperation(value = "新增分类")
    @PostMapping("/insert")
    public Result insert(@Validated @RequestBody SysCategory sysCategory) {
//        if (!SysCategoryService.checkUserNameUnique(sysCategory)) {
//            return error("新增分类'" + sysCategory.getUserName() + "'失败，分类账号已存在");
//        }
        return isSuccess(SysCategoryService.insert(sysCategory));
    }

    @ApiOperation(value = "修改分类")
    @PostMapping("/update")
    public Result update(@Validated @RequestBody SysCategory sysCategory) {
        return isSuccess(SysCategoryService.update(sysCategory));
    }

    @ApiOperation(value = "批量删除分类")
    @DeleteMapping("/batchDelete/{ids}")
    public Result batchDelete(@PathVariable Long[] ids) {
        return isSuccess(SysCategoryService.deleteByIds(ids));
    }
}
