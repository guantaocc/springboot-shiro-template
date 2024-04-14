package com.rain.shiro.project.service.impl;

import java.util.List;

import com.rain.shiro.commons.utils.DateUtils;
import com.rain.shiro.commons.utils.ShiroUtils;
import com.rain.shiro.project.entity.SysCategory;
import com.rain.shiro.project.service.SysCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rain.shiro.project.mapper.SysCategoryMapper;

/**
 * 用户信息Service业务层处理
 */
@Service
public class SysCategoryServiceImpl implements SysCategoryService {
    @Autowired
    private SysCategoryMapper SysCategoryMapper;

    @Override
    public SysCategory selectById(Long id) {
        return SysCategoryMapper.selectById(id);
    }


    @Override
    public List<SysCategory> selectList(SysCategory SysCategory) {
        return SysCategoryMapper.selectList(SysCategory);
    }


    @Override
    public int insert(SysCategory SysCategory) {
        SysCategory.setCreateBy(ShiroUtils.getUserName());
        SysCategory.setCreateTime(DateUtils.getNowDate());
        return SysCategoryMapper.insert(SysCategory);
    }


    @Override
    public int update(SysCategory SysCategory) {
        SysCategory.setUpdateTime(DateUtils.getNowDate());
        SysCategory.setUpdateBy(ShiroUtils.getUserName());
        return SysCategoryMapper.update(SysCategory);
    }


    @Override
    public int deleteByIds(Long[] ids) {
        return SysCategoryMapper.deleteByIds(ids);
    }


    @Override
    public int deleteById(Long id) {
        return SysCategoryMapper.deleteById(id);
    }


    @Override
    public SysCategory selectByName(String name) {
        return SysCategoryMapper.selectByName(name);
    }

}
