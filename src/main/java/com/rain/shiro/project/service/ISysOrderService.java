package com.rain.shiro.project.service;

import com.rain.shiro.project.entity.SysOrder;

import java.util.List;

/**
 * 订单Service接口
 *
 * @author ruoyi
 * @date 2024-04-20
 */
public interface ISysOrderService
{
    /**
     * 查询订单
     *
     * @param id 订单主键
     * @return 订单
     */
    public SysOrder selectSysOrderById(Long id);

    /**
     * 查询订单列表
     *
     * @param sysOrder 订单
     * @return 订单集合
     */
    public List<SysOrder> selectSysOrderList(SysOrder sysOrder);

    /**
     * 新增订单
     *
     * @param sysOrder 订单
     * @return 结果
     */
    public int insertSysOrder(SysOrder sysOrder);

    /**
     * 修改订单
     *
     * @param sysOrder 订单
     * @return 结果
     */
    public int updateSysOrder(SysOrder sysOrder);

    /**
     * 批量删除订单
     *
     * @param ids 需要删除的订单主键集合
     * @return 结果
     */
    public int deleteSysOrderByIds(Long[] ids);

    /**
     * 删除订单信息
     *
     * @param id 订单主键
     * @return 结果
     */
    public int deleteSysOrderById(Long id);

    public int getTodayNewCount();

    public int countAll();
}
