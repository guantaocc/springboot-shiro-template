package com.rain.shiro.project.service.impl;

import java.util.List;
import com.rain.shiro.commons.utils.DateUtils;
import com.rain.shiro.commons.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rain.shiro.project.mapper.SysPackageMapper;
import com.rain.shiro.project.entity.SysPackage;
import com.rain.shiro.project.service.ISysPackageService;

/**
 * 套餐Service业务层处理
 *
 * @author guantao
 * @date 2024-04-14
 */
@Service
public class SysPackageServiceImpl implements ISysPackageService
{
    @Autowired
    private SysPackageMapper sysPackageMapper;

    /**
     * 查询套餐
     *
     * @param id 套餐主键
     * @return 套餐
     */
    @Override
    public SysPackage selectSysPackageById(Long id)
    {
        return sysPackageMapper.selectSysPackageById(id);
    }

    /**
     * 查询套餐列表
     *
     * @param sysPackage 套餐
     * @return 套餐
     */
    @Override
    public List<SysPackage> selectSysPackageList(SysPackage sysPackage)
    {
        return sysPackageMapper.selectSysPackageList(sysPackage);
    }

    /**
     * 新增套餐
     *
     * @param sysPackage 套餐
     * @return 结果
     */
    @Override
    public int insertSysPackage(SysPackage sysPackage)
    {
        sysPackage.setCreateBy(ShiroUtils.getUserName());
        sysPackage.setCreateTime(DateUtils.getNowDate());
        return sysPackageMapper.insertSysPackage(sysPackage);
    }

    /**
     * 修改套餐
     *
     * @param sysPackage 套餐
     * @return 结果
     */
    @Override
    public int updateSysPackage(SysPackage sysPackage)
    {
        sysPackage.setUpdateTime(DateUtils.getNowDate());
        sysPackage.setUpdateBy(ShiroUtils.getUserName());
        return sysPackageMapper.updateSysPackage(sysPackage);
    }

    /**
     * 批量删除套餐
     *
     * @param ids 需要删除的套餐主键
     * @return 结果
     */
    @Override
    public int deleteSysPackageByIds(Long[] ids)
    {
        return sysPackageMapper.deleteSysPackageByIds(ids);
    }

    /**
     * 删除套餐信息
     *
     * @param id 套餐主键
     * @return 结果
     */
    @Override
    public int deleteSysPackageById(Long id)
    {
        return sysPackageMapper.deleteSysPackageById(id);
    }
}
