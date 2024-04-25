package com.rain.shiro.project.service;

import com.rain.shiro.project.mapper.StatisticsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StatisticsService {

    @Autowired
    private StatisticsMapper statisticsMapper;

    public List<Map<String, Object>> getStatistics() {
        return statisticsMapper.getStatistics();
    }
}