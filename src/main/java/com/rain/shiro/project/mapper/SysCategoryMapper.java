package com.rain.shiro.project.mapper;

import java.util.List;

import com.rain.shiro.project.entity.SysCategory;

/**
 * 用户信息Mapper接口
 */
public interface SysCategoryMapper {
    /**
     * 查询用户信息
     *
     * @param id 用户信息主键
     * @return 用户信息
     */
    public SysCategory selectById(Long id);

    /**
     * 查询分类信息列表
     *
     * @param SysCategory 分类信息
     * @return 分类信息集合
     */
    public List<SysCategory> selectList(SysCategory SysCategory);

    /**
     * 新增分类
     *
     * @param SysCategory 分类信息
     * @return 结果
     */
    public int insert(SysCategory SysCategory);

    /**
     * 修改分类信息
     *
     * @param SysCategory 分类信息
     * @return 结果
     */
    public int update(SysCategory SysCategory);

    /**
     * 批量删除分类信息
     *
     * @param ids 需要删除的用户信息主键集合
     * @return 结果
     */
    public int deleteByIds(Long[] ids);

    /**
     * 删除分类信息
     *
     * @param id 用户信息主键
     * @return 结果
     */
    public int deleteById(Long id);

    /**
     * 查询分类信息
     *
     * @param name 分类名
     * @return 分类信息
     */
    SysCategory selectByName(String name);
}
