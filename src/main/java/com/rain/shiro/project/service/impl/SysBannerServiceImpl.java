package com.rain.shiro.project.service.impl;

import java.util.List;
import com.rain.shiro.commons.utils.DateUtils;
import com.rain.shiro.project.entity.SysBanner;
import com.rain.shiro.project.mapper.SysBannerMapper;
import com.rain.shiro.project.service.ISysBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 资讯Service业务层处理
 *
 * @author ruoyi
 * @date 2024-04-20
 */
@Service
public class SysBannerServiceImpl implements ISysBannerService
{
    @Autowired
    private SysBannerMapper sysBannerMapper;

    /**
     * 查询资讯
     *
     * @param id 资讯主键
     * @return 资讯
     */
    @Override
    public SysBanner selectSysBannerById(Long id)
    {
        return sysBannerMapper.selectSysBannerById(id);
    }

    /**
     * 查询资讯列表
     *
     * @param sysBanner 资讯
     * @return 资讯
     */
    @Override
    public List<SysBanner> selectSysBannerList(SysBanner sysBanner)
    {
        return sysBannerMapper.selectSysBannerList(sysBanner);
    }

    /**
     * 新增资讯
     *
     * @param sysBanner 资讯
     * @return 结果
     */
    @Override
    public int insertSysBanner(SysBanner sysBanner)
    {
        sysBanner.setCreateTime(DateUtils.getNowDate());
        return sysBannerMapper.insertSysBanner(sysBanner);
    }

    /**
     * 修改资讯
     *
     * @param sysBanner 资讯
     * @return 结果
     */
    @Override
    public int updateSysBanner(SysBanner sysBanner)
    {
        sysBanner.setUpdateTime(DateUtils.getNowDate());
        return sysBannerMapper.updateSysBanner(sysBanner);
    }

    /**
     * 批量删除资讯
     *
     * @param ids 需要删除的资讯主键
     * @return 结果
     */
    @Override
    public int deleteSysBannerByIds(Long[] ids)
    {
        return sysBannerMapper.deleteSysBannerByIds(ids);
    }

    /**
     * 删除资讯信息
     *
     * @param id 资讯主键
     * @return 结果
     */
    @Override
    public int deleteSysBannerById(Long id)
    {
        return sysBannerMapper.deleteSysBannerById(id);
    }
}
