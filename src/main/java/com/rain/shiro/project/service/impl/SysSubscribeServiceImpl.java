package com.rain.shiro.project.service.impl;

import java.util.List;
import com.rain.shiro.commons.utils.DateUtils;
import com.rain.shiro.project.entity.SysSubscribe;
import com.rain.shiro.project.mapper.SysSubscribeMapper;
import com.rain.shiro.project.service.ISysSubscribeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 预约Service业务层处理
 *
 * @author ruoyi
 * @date 2024-04-19
 */
@Service
public class SysSubscribeServiceImpl implements ISysSubscribeService
{
    @Autowired
    private SysSubscribeMapper sysSubscribeMapper;

    /**
     * 查询预约
     *
     * @param id 预约主键
     * @return 预约
     */
    @Override
    public SysSubscribe selectSysSubscribeById(Long id)
    {
        return sysSubscribeMapper.selectSysSubscribeById(id);
    }

    /**
     * 查询预约列表
     *
     * @param sysSubscribe 预约
     * @return 预约
     */
    @Override
    public List<SysSubscribe> selectSysSubscribeList(SysSubscribe sysSubscribe)
    {
        return sysSubscribeMapper.selectSysSubscribeList(sysSubscribe);
    }

    /**
     * 新增预约
     *
     * @param sysSubscribe 预约
     * @return 结果
     */
    @Override
    public int insertSysSubscribe(SysSubscribe sysSubscribe)
    {
        sysSubscribe.setCreateTime(DateUtils.getNowDate());
        return sysSubscribeMapper.insertSysSubscribe(sysSubscribe);
    }

    /**
     * 修改预约
     *
     * @param sysSubscribe 预约
     * @return 结果
     */
    @Override
    public int updateSysSubscribe(SysSubscribe sysSubscribe)
    {
        sysSubscribe.setUpdateTime(DateUtils.getNowDate());
        return sysSubscribeMapper.updateSysSubscribe(sysSubscribe);
    }

    /**
     * 批量删除预约
     *
     * @param ids 需要删除的预约主键
     * @return 结果
     */
    @Override
    public int deleteSysSubscribeByIds(Long[] ids)
    {
        return sysSubscribeMapper.deleteSysSubscribeByIds(ids);
    }

    /**
     * 删除预约信息
     *
     * @param id 预约主键
     * @return 结果
     */
    @Override
    public int deleteSysSubscribeById(Long id)
    {
        return sysSubscribeMapper.deleteSysSubscribeById(id);
    }
}
