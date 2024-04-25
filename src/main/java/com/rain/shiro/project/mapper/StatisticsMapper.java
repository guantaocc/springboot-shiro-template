package com.rain.shiro.project.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface StatisticsMapper {
    List<Map<String, Object>> getStatistics();
}