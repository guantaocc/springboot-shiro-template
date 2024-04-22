package com.rain.shiro.project.controller;

import java.lang.reflect.Field;
import java.util.*;

import com.rain.shiro.commons.core.result.Result;
import com.rain.shiro.project.entity.SysSubscribe;
import com.rain.shiro.project.service.ISysPackageService;
import com.rain.shiro.project.service.ISysSubscribeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import com.rain.shiro.commons.core.base.BaseController;
import com.rain.shiro.commons.utils.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api(tags = "预约接口")
@RestController
@RequestMapping("/subscribe")
public class SysSubscribeController extends BaseController {

    @Autowired
    private ISysSubscribeService sysSubscribeService;

    @Autowired
    private ISysPackageService sysPackageService;


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



    @GetMapping("/all")
    public Result all(SysSubscribe sysSubscribe) {
        List<SysSubscribe> list = sysSubscribeService.selectSysSubscribeList(sysSubscribe);
        ArrayList<Map<String, Object>> mixedList = new ArrayList<>();

        for (SysSubscribe subscribe : list) {
            long id = Long.parseLong(subscribe.getPackageId());
            Object packageInfo = sysPackageService.selectSysPackageById(id);

            // 创建一个包含 subscribe 字段的 Map
            Map<String, Object> mixedEntry = new HashMap<>();
            mixedEntry.put("packageInfo", packageInfo);

            // 使用反射获取 SysSubscribe 类及其父类的所有字段
            List<Field> allFields = new ArrayList<>();
            Class<?> clazz = subscribe.getClass();
            while (clazz != null) {
                allFields.addAll(Arrays.asList(clazz.getDeclaredFields()));
                clazz = clazz.getSuperclass();
            }

            for (Field field : allFields) {
                // 设置可访问，防止private导致异常
                field.setAccessible(true);
                try {
                    mixedEntry.put(field.getName(), field.get(subscribe));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            // 将 mixedEntry 加入到 mixedList 中
            mixedList.add(mixedEntry);
        }
        return success(mixedList);
    }

    @ApiOperation(value = "获取单个预约")
    @ApiImplicitParam(name = "id", value = "分类ID", dataType = "int", paramType = "query", required = true)
    @GetMapping(value = "/getInfo")
    public Result getInfo(Long id) {
        SysSubscribe subscribe = sysSubscribeService.selectSysSubscribeById(id);
        long s_id = Long.parseLong(subscribe.getPackageId());
        Object packageInfo = sysPackageService.selectSysPackageById(s_id);

        // 创建一个包含 subscribe 字段的 Map
        Map<String, Object> mixedEntry = new HashMap<>();
        mixedEntry.put("packageInfo", packageInfo);

        // 使用反射获取 SysSubscribe 类及其父类的所有字段
        List<Field> allFields = new ArrayList<>();
        Class<?> clazz = subscribe.getClass();
        while (clazz != null) {
            allFields.addAll(Arrays.asList(clazz.getDeclaredFields()));
            clazz = clazz.getSuperclass();
        }

        for (Field field : allFields) {
            // 设置可访问，防止private导致异常
            field.setAccessible(true);
            try {
                mixedEntry.put(field.getName(), field.get(subscribe));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return success(mixedEntry);
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
    @DeleteMapping("/batchDelete/{ids}")
    public Result batchDelete(@PathVariable Long[] ids) {
        return isSuccess(sysSubscribeService.deleteSysSubscribeByIds(ids));
    }
}
