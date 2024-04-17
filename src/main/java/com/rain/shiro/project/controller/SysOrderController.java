package com.rain.shiro.project.controller;

import java.util.List;

import com.rain.shiro.commons.core.result.Result;
import com.rain.shiro.project.entity.SysOrder;
import com.rain.shiro.project.service.ISysOrderService;
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

@Api(tags = "订单接口")
@RestController
@RequestMapping("/order")
public class SysOrderController extends BaseController {

    @Autowired
    private ISysOrderService sysOrderService;

    @ApiOperation(value = "查询订单列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", defaultValue = "1", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "页数", defaultValue = "10", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "orderByColumn", value = "排序字段", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "isAsc", value = "asc-升序, desc-降序", dataType = "string", paramType = "query")
    })
    @GetMapping("/list")
    public TableDataInfo list(SysOrder sysOrder) {
        startPage();
        List<SysOrder> list = sysOrderService.selectSysOrderList(sysOrder);
        return getDataTable(list);
    }

    @ApiOperation(value = "获取单个订单")
    @ApiImplicitParam(name = "id", value = "分类ID", dataType = "int", paramType = "query", required = true)
    @GetMapping(value = "/getInfo")
    public Result getInfo(Long id) {
        return success(sysOrderService.selectSysOrderById(id));
    }

    @ApiOperation(value = "新增订单")
    @PostMapping("/insert")
    public Result insert(@Validated @RequestBody SysOrder sysOrder) {
        return isSuccess(sysOrderService.insertSysOrder(sysOrder));
    }

    @ApiOperation(value = "修改订单")
    @PostMapping("/update")
    public Result update(@Validated @RequestBody SysOrder sysOrder) {
        return isSuccess(sysOrderService.updateSysOrder(sysOrder));
    }

    @ApiOperation(value = "批量删除订单")
    @PostMapping("/batchDelete")
    public Result batchDelete(@PathVariable Long[] ids) {
        return isSuccess(sysOrderService.deleteSysOrderByIds(ids));
    }
}
