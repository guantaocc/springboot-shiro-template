package com.rain.shiro.project.controller;

import java.lang.reflect.Field;
import java.util.*;

import com.rain.shiro.commons.core.result.Result;
import com.rain.shiro.commons.utils.sign.Md5Utils;
import com.rain.shiro.project.entity.*;
import com.rain.shiro.project.service.*;
import com.rain.shiro.project.templates.GenerateReport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import com.rain.shiro.commons.core.base.BaseController;
import com.rain.shiro.commons.utils.page.TableDataInfo;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private ISysPackageService sysPackageService;

    @Autowired
    private ISysSubscribeService sysSubscribeService;

    @Autowired
    private ISysInstitutionService sysInstitutionService;

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
        ArrayList<Map<String, Object>> mixedList = mixDetailInfo(list);
        return getDataTable(mixedList);
    }

    @GetMapping("/all")
    public Result all(SysOrder sysOrder) {
        List<SysOrder> list = sysOrderService.selectSysOrderList(sysOrder);
        ArrayList<Map<String, Object>> mixedList = mixDetailInfo(list);
        return success(mixedList);
    }

    /**
     * 生成体检报告
     * @param
     * @return
     */

    @PostMapping("/generateReport")
    public Result generageReport(@RequestBody Map<String, ?> obj) throws Exception {
        System.out.println(obj);
        GenerateReport report = new GenerateReport();
        String reportUrl = (String) obj.get("reportUrl");
        String filePath = report.generateSubscribeReport(obj, reportUrl);
        Long id = new Long(String.valueOf(obj.get("id")));
        SysOrder sysOrder = sysOrderService.selectSysOrderById(id);
        sysOrder.setReportUrl(filePath);
        sysOrderService.updateSysOrder(sysOrder);
        return success("生成成功");
    }

    /**
     * 查询详情方法类
     * @param list
     * @return
     */

    public ArrayList mixDetailInfo(List<SysOrder> list){
        ArrayList<Map<String, Object>> mixedList = new ArrayList<>();

        for (SysOrder order : list) {
            long userId = order.getUserId();
            SysUser sysUser = sysUserService.selectSysUserByUserId(userId);
            long packageId = order.getPackageId();
            SysPackage sysPackage = sysPackageService.selectSysPackageById(packageId);
            long subscribeId = order.getSubscribeId();
            SysSubscribe sysSubscribe = sysSubscribeService.selectSysSubscribeById(subscribeId);
            long insititutionId = order.getInstitutionId();
            SysInstitution sysInstitution = sysInstitutionService.selectSysInstitutionById(insititutionId);
            // 反射 order所有字段
            // 创建一个包含 subscribe 字段的 Map

            Map<String, Object> mixedEntry = new HashMap<>();
            mixedEntry.put("userInfo", sysUser);
            // 添加关联的信息
            mixedEntry.put("packageInfo", sysPackage);
            mixedEntry.put("subscribeInfo", sysSubscribe);
            mixedEntry.put("insititutionInfo", sysInstitution);

            // 使用反射获取 order 类及其父类的所有字段
            List<Field> allFields = new ArrayList<>();
            Class<?> clazz = order.getClass();
            while (clazz != null) {
                allFields.addAll(Arrays.asList(clazz.getDeclaredFields()));
                clazz = clazz.getSuperclass();
            }

            for (Field field : allFields) {
                // 设置可访问，防止private导致异常
                field.setAccessible(true);
                try {
                    mixedEntry.put(field.getName(), field.get(order));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            // 将 mixedEntry 加入到 mixedList 中
            mixedList.add(mixedEntry);
        }
        return mixedList;
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
