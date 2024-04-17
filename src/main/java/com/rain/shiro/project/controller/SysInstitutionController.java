package com.rain.shiro.project.controller;

import java.util.List;

import com.rain.shiro.commons.core.result.Result;
import com.rain.shiro.project.entity.SysInstitution;
import com.rain.shiro.project.service.ISysInstitutionService;
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

@Api(tags = "机构接口")
@RestController
@RequestMapping("/institution")
public class SysInstitutionController extends BaseController {

    @Autowired
    private ISysInstitutionService sysInstitutionService;

    @ApiOperation(value = "查询机构列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", defaultValue = "1", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "页数", defaultValue = "10", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "orderByColumn", value = "排序字段", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "isAsc", value = "asc-升序, desc-降序", dataType = "string", paramType = "query")
    })
    @GetMapping("/list")
    public TableDataInfo list(SysInstitution sysInstitution) {
        startPage();
        List<SysInstitution> list = sysInstitutionService.selectSysInstitutionList(sysInstitution);
        return getDataTable(list);
    }

    @ApiOperation(value = "获取单个机构")
    @ApiImplicitParam(name = "id", value = "分类ID", dataType = "int", paramType = "query", required = true)
    @GetMapping(value = "/getInfo")
    public Result getInfo(Long id) {
        return success(sysInstitutionService.selectSysInstitutionById(id));
    }

    @ApiOperation(value = "新增机构")
    @PostMapping("/insert")
    public Result insert(@Validated @RequestBody SysInstitution sysInstitution) {
        return isSuccess(sysInstitutionService.insertSysInstitution(sysInstitution));
    }

    @ApiOperation(value = "修改机构")
    @PostMapping("/update")
    public Result update(@Validated @RequestBody SysInstitution sysInstitution) {
        return isSuccess(sysInstitutionService.updateSysInstitution(sysInstitution));
    }

    @ApiOperation(value = "批量删除机构")
    @PostMapping("/batchDelete")
    public Result batchDelete(@PathVariable Long[] ids) {
        return isSuccess(sysInstitutionService.deleteSysInstitutionByIds(ids));
    }
}
