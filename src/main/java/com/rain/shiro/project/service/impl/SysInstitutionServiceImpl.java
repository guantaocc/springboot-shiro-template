package com.rain.shiro.project.service.impl;

import java.util.List;
import com.rain.shiro.commons.utils.DateUtils;
import com.rain.shiro.project.entity.SysInstitution;
import com.rain.shiro.project.mapper.SysInstitutionMapper;
import com.rain.shiro.project.service.ISysInstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 机构Service业务层处理
 *
 * @author ruoyi
 * @date 2024-04-17
 */
@Service
public class SysInstitutionServiceImpl implements ISysInstitutionService
{
    @Autowired
    private SysInstitutionMapper sysInstitutionMapper;

    /**
     * 查询机构
     *
     * @param id 机构主键
     * @return 机构
     */
    @Override
    public SysInstitution selectSysInstitutionById(Long id)
    {
        return sysInstitutionMapper.selectSysInstitutionById(id);
    }

    /**
     * 查询机构列表
     *
     * @param sysInstitution 机构
     * @return 机构
     */
    @Override
    public List<SysInstitution> selectSysInstitutionList(SysInstitution sysInstitution)
    {
        return sysInstitutionMapper.selectSysInstitutionList(sysInstitution);
    }

    /**
     * 新增机构
     *
     * @param sysInstitution 机构
     * @return 结果
     */
    @Override
    public int insertSysInstitution(SysInstitution sysInstitution)
    {
        sysInstitution.setCreateTime(DateUtils.getNowDate());
        return sysInstitutionMapper.insertSysInstitution(sysInstitution);
    }

    /**
     * 修改机构
     *
     * @param sysInstitution 机构
     * @return 结果
     */
    @Override
    public int updateSysInstitution(SysInstitution sysInstitution)
    {
        sysInstitution.setUpdateTime(DateUtils.getNowDate());
        return sysInstitutionMapper.updateSysInstitution(sysInstitution);
    }

    /**
     * 批量删除机构
     *
     * @param ids 需要删除的机构主键
     * @return 结果
     */
    @Override
    public int deleteSysInstitutionByIds(Long[] ids)
    {
        return sysInstitutionMapper.deleteSysInstitutionByIds(ids);
    }

    /**
     * 删除机构信息
     *
     * @param id 机构主键
     * @return 结果
     */
    @Override
    public int deleteSysInstitutionById(Long id)
    {
        return sysInstitutionMapper.deleteSysInstitutionById(id);
    }
}
