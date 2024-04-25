package com.rain.shiro.project.controller;

import com.rain.shiro.commons.core.result.Result;
import com.rain.shiro.project.service.ISysOrderService;
import com.rain.shiro.project.service.ISysSubscribeService;
import com.rain.shiro.project.service.StatisticsService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

import static com.rain.shiro.commons.core.result.Result.success;

@Api(tags = "统计接口")
@RestController
@RequestMapping("/count")
public class CountController {

    @Autowired
    ISysOrderService sysOrderService;

    @Autowired
    ISysSubscribeService sysSubscribeService;

    @Autowired
    StatisticsService statisticsService;

    @GetMapping("/home")
    public Result dashboard() {
        HashMap<String, Integer> map = new HashMap<>();
        int orderTodayNow = sysOrderService.getTodayNewCount();
        map.put("orderTodayNow", orderTodayNow);
        int orderAll = sysOrderService.countAll();
        map.put("orderAll", orderAll);
        int subscribeTodayNow = sysSubscribeService.getTodayNewCount();
        int subscribeAll = sysSubscribeService.countAll();
        map.put("subscribeTodayNow", subscribeTodayNow);
        map.put("subscribeAll", subscribeAll);
        return success(map);
    }

    @GetMapping("/overview")
    public Result overview() {
        return success(statisticsService.getStatistics());
    }
}
