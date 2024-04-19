package com.rain.shiro.project.mapper;

import com.rain.shiro.project.entity.SysSubscribe;

import java.util.List;

/**
 * 预约Mapper接口
 *
 * @author ruoyi
 * @date 2024-04-19
 */
public interface SysSubscribeMapper
{
    /**
     * 查询预约
     *
     * @param id 预约主键
     * @return 预约
     */
    public SysSubscribe selectSysSubscribeById(Long id);

    /**
     * 查询预约列表
     *
     * @param sysSubscribe 预约
     * @return 预约集合
     */
    public List<SysSubscribe> selectSysSubscribeList(SysSubscribe sysSubscribe);

    /**
     * 新增预约
     *
     * @param sysSubscribe 预约
     * @return 结果
     */
    public int insertSysSubscribe(SysSubscribe sysSubscribe);

    /**
     * 修改预约
     *
     * @param sysSubscribe 预约
     * @return 结果
     */
    public int updateSysSubscribe(SysSubscribe sysSubscribe);

    /**
     * 删除预约
     *
     * @param id 预约主键
     * @return 结果
     */
    public int deleteSysSubscribeById(Long id);

    /**
     * 批量删除预约
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysSubscribeByIds(Long[] ids);
}
