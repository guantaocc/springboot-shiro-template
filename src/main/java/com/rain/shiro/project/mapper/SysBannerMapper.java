package com.rain.shiro.project.mapper;

import com.rain.shiro.project.entity.SysBanner;

import java.util.List;

/**
 * 资讯Mapper接口
 *
 * @author ruoyi
 * @date 2024-04-20
 */
public interface SysBannerMapper
{
    /**
     * 查询资讯
     *
     * @param id 资讯主键
     * @return 资讯
     */
    public SysBanner selectSysBannerById(Long id);

    /**
     * 查询资讯列表
     *
     * @param sysBanner 资讯
     * @return 资讯集合
     */
    public List<SysBanner> selectSysBannerList(SysBanner sysBanner);

    /**
     * 新增资讯
     *
     * @param sysBanner 资讯
     * @return 结果
     */
    public int insertSysBanner(SysBanner sysBanner);

    /**
     * 修改资讯
     *
     * @param sysBanner 资讯
     * @return 结果
     */
    public int updateSysBanner(SysBanner sysBanner);

    /**
     * 删除资讯
     *
     * @param id 资讯主键
     * @return 结果
     */
    public int deleteSysBannerById(Long id);

    /**
     * 批量删除资讯
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysBannerByIds(Long[] ids);
}
