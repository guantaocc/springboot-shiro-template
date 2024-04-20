package com.rain.shiro.project.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.rain.shiro.commons.utils.DateUtils;
import com.rain.shiro.project.entity.SysOrder;
import com.rain.shiro.project.entity.SysSubscribe;
import com.rain.shiro.project.mapper.SysOrderMapper;
import com.rain.shiro.project.mapper.SysSubscribeMapper;
import com.rain.shiro.project.service.ISysOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 订单Service业务层处理
 *
 * @author ruoyi
 * @date 2024-04-20
 */
@Service
public class SysOrderServiceImpl implements ISysOrderService
{
    @Autowired
    private SysOrderMapper sysOrderMapper;

    @Autowired
    private SysSubscribeMapper sysSubscribeMapper;
    /**
     * 查询订单
     *
     * @param id 订单主键
     * @return 订单
     */
    @Override
    public SysOrder selectSysOrderById(Long id)
    {
        return sysOrderMapper.selectSysOrderById(id);
    }

    /**
     * 查询订单列表
     *
     * @param sysOrder 订单
     * @return 订单
     */
    @Override
    public List<SysOrder> selectSysOrderList(SysOrder sysOrder)
    {
        return sysOrderMapper.selectSysOrderList(sysOrder);
    }

    /**
     * 新增订单
     *
     * @param sysOrder 订单
     * @return 结果
     */
    @Override
    public int insertSysOrder(SysOrder sysOrder)
    {
        // 随机生成一个订单id
        String id  = String.valueOf(UUID.randomUUID());
        // 更新当前预约记录状态为已支付
        long subscribeId = sysOrder.getSubscribeId();
        SysSubscribe sysSubscribe = sysSubscribeMapper.selectSysSubscribeById(subscribeId);
        sysSubscribe.setOrderStatus("1");
        sysSubscribeMapper.updateSysSubscribe(sysSubscribe);
        // 创建当前订单;
        sysOrder.setOrderId(id);
        sysOrder.setCreateTime(DateUtils.getNowDate());
        return sysOrderMapper.insertSysOrder(sysOrder);
    }

    /**
     * 修改订单
     *
     * @param sysOrder 订单
     * @return 结果
     */
    @Override
    public int updateSysOrder(SysOrder sysOrder)
    {
        sysOrder.setUpdateTime(DateUtils.getNowDate());
        return sysOrderMapper.updateSysOrder(sysOrder);
    }

    /**
     * 批量删除订单
     *
     * @param ids 需要删除的订单主键
     * @return 结果
     */
    @Override
    public int deleteSysOrderByIds(Long[] ids)
    {
        return sysOrderMapper.deleteSysOrderByIds(ids);
    }

    /**
     * 删除订单信息
     *
     * @param id 订单主键
     * @return 结果
     */
    @Override
    public int deleteSysOrderById(Long id)
    {
        return sysOrderMapper.deleteSysOrderById(id);
    }

    public int getTodayNewCount() {
        LocalDateTime startOfDay = LocalDate.now().atStartOfDay();
        LocalDateTime endOfDay = LocalDateTime.now();
        return sysOrderMapper.countTodayNew(startOfDay, endOfDay);
    }

    public int countAll(){
        return sysOrderMapper.countAll();
    }
}
