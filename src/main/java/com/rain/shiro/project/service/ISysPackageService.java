package com.rain.shiro.project.service;

import java.util.List;
import com.rain.shiro.project.entity.SysPackage;

/**
 * 套餐Service接口
 *
 * @author guantao
 * @date 2024-04-14
 */
public interface ISysPackageService
{
    /**
     * 查询套餐
     *
     * @param id 套餐主键
     * @return 套餐
     */
    public SysPackage selectSysPackageById(Long id);

    /**
     * 查询套餐列表
     *
     * @param sysPackage 套餐
     * @return 套餐集合
     */
    public List<SysPackage> selectSysPackageList(SysPackage sysPackage);

    /**
     * 新增套餐
     *
     * @param sysPackage 套餐
     * @return 结果
     */
    public int insertSysPackage(SysPackage sysPackage);

    /**
     * 修改套餐
     *
     * @param sysPackage 套餐
     * @return 结果
     */
    public int updateSysPackage(SysPackage sysPackage);

    /**
     * 批量删除套餐
     *
     * @param ids 需要删除的套餐主键集合
     * @return 结果
     */
    public int deleteSysPackageByIds(Long[] ids);

    /**
     * 删除套餐信息
     *
     * @param id 套餐主键
     * @return 结果
     */
    public int deleteSysPackageById(Long id);
}
