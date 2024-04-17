package com.rain.shiro.project.service;

import com.rain.shiro.project.entity.SysInstitution;

import java.util.List;

/**
 * 机构Service接口
 *
 * @author ruoyi
 * @date 2024-04-17
 */
public interface ISysInstitutionService
{
    /**
     * 查询机构
     *
     * @param id 机构主键
     * @return 机构
     */
    public SysInstitution selectSysInstitutionById(Long id);

    /**
     * 查询机构列表
     *
     * @param sysInstitution 机构
     * @return 机构集合
     */
    public List<SysInstitution> selectSysInstitutionList(SysInstitution sysInstitution);

    /**
     * 新增机构
     *
     * @param sysInstitution 机构
     * @return 结果
     */
    public int insertSysInstitution(SysInstitution sysInstitution);

    /**
     * 修改机构
     *
     * @param sysInstitution 机构
     * @return 结果
     */
    public int updateSysInstitution(SysInstitution sysInstitution);

    /**
     * 批量删除机构
     *
     * @param ids 需要删除的机构主键集合
     * @return 结果
     */
    public int deleteSysInstitutionByIds(Long[] ids);

    /**
     * 删除机构信息
     *
     * @param id 机构主键
     * @return 结果
     */
    public int deleteSysInstitutionById(Long id);
}
