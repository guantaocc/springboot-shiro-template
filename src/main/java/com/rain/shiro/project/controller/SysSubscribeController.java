package com.rain.shiro.project.controller;

import java.util.List;

import com.rain.shiro.commons.core.result.Result;
import com.rain.shiro.project.entity.SysSubscribe;
import com.rain.shiro.project.service.ISysSubscribeService;
import com.rain.shiro.project.templates.GenerateReport;
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

@Api(tags = "预约接口")
@RestController
@RequestMapping("/subscribe")
public class SysSubscribeController extends BaseController {

    @Autowired
    private ISysSubscribeService sysSubscribeService;


    @ApiOperation(value = "查询预约列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", defaultValue = "1", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "页数", defaultValue = "10", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "orderByColumn", value = "排序字段", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "isAsc", value = "asc-升序, desc-降序", dataType = "string", paramType = "query")
    })
    @GetMapping("/list")
    public TableDataInfo list(SysSubscribe sysSubscribe) {
        startPage();
        List<SysSubscribe> list = sysSubscribeService.selectSysSubscribeList(sysSubscribe);
        return getDataTable(list);
    }

    @PostMapping("/generateReport")
    public Result generageReport(@RequestBody Object obj) throws Exception {
        System.out.println(obj);
        GenerateReport report = new GenerateReport();
        report.generateSubscribeReport(obj);
        return success("生成成功");
    }

    @ApiOperation(value = "获取单个预约")
    @ApiImplicitParam(name = "id", value = "分类ID", dataType = "int", paramType = "query", required = true)
    @GetMapping(value = "/getInfo")
    public Result getInfo(Long id) {
        return success(sysSubscribeService.selectSysSubscribeById(id));
    }

    @ApiOperation(value = "新增预约")
    @PostMapping("/insert")
    public Result insert(@Validated @RequestBody SysSubscribe sysSubscribe) {
        return isSuccess(sysSubscribeService.insertSysSubscribe(sysSubscribe));
    }

    @ApiOperation(value = "修改预约")
    @PostMapping("/update")
    public Result update(@Validated @RequestBody SysSubscribe sysSubscribe) {
        return isSuccess(sysSubscribeService.updateSysSubscribe(sysSubscribe));
    }

    @ApiOperation(value = "批量删除预约")
    @PostMapping("/batchDelete")
    public Result batchDelete(@PathVariable Long[] ids) {
        return isSuccess(sysSubscribeService.deleteSysSubscribeByIds(ids));
    }
}
