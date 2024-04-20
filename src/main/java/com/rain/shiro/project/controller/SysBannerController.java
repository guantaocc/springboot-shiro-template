package com.rain.shiro.project.controller;

import java.util.List;

import com.rain.shiro.commons.core.result.Result;
import com.rain.shiro.commons.utils.sign.Md5Utils;
import com.rain.shiro.project.entity.SysBanner;
import com.rain.shiro.project.entity.SysCategory;
import com.rain.shiro.project.service.ISysBannerService;
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

@Api(tags = "资讯接口")
@RestController
@RequestMapping("/banner")
public class SysBannerController extends BaseController {

    @Autowired
    private ISysBannerService sysBannerService;

    @ApiOperation(value = "查询资讯列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", defaultValue = "1", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "页数", defaultValue = "10", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "orderByColumn", value = "排序字段", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "isAsc", value = "asc-升序, desc-降序", dataType = "string", paramType = "query")
    })
    @GetMapping("/list")
    public TableDataInfo list(SysBanner sysBanner) {
        startPage();
        List<SysBanner> list = sysBannerService.selectSysBannerList(sysBanner);
        return getDataTable(list);
    }

    @GetMapping("/all")
    public Result all(SysBanner sysBanner) {
        List<SysBanner> list = sysBannerService.selectSysBannerList(sysBanner);
        return success(list);
    }

    @ApiOperation(value = "获取单个资讯")
    @ApiImplicitParam(name = "id", value = "分类ID", dataType = "int", paramType = "query", required = true)
    @GetMapping(value = "/getInfo")
    public Result getInfo(Long id) {
        return success(sysBannerService.selectSysBannerById(id));
    }

    @ApiOperation(value = "新增资讯")
    @PostMapping("/insert")
    public Result insert(@Validated @RequestBody SysBanner sysBanner) {
        return isSuccess(sysBannerService.insertSysBanner(sysBanner));
    }

    @ApiOperation(value = "修改资讯")
    @PostMapping("/update")
    public Result update(@Validated @RequestBody SysBanner sysBanner) {
        return isSuccess(sysBannerService.updateSysBanner(sysBanner));
    }

    @ApiOperation(value = "批量删除资讯")
    @PostMapping("/batchDelete")
    public Result batchDelete(@PathVariable Long[] ids) {
        return isSuccess(sysBannerService.deleteSysBannerByIds(ids));
    }
}
