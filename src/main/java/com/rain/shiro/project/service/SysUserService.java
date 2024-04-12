package com.rain.shiro.project.service;

import java.util.List;
import com.rain.shiro.project.entity.SysUser;

/**
 * 用户信息Service接口
 */
public interface SysUserService {
    /**
     * 查询用户信息
     * 
     * @param userId 用户信息主键
     * @return 用户信息
     */
    public SysUser selectSysUserByUserId(Long userId);

    /**
     * 查询用户信息列表
     * 
     * @param sysUser 用户信息
     * @return 用户信息集合
     */
    public List<SysUser> selectSysUserList(SysUser sysUser);

    /**
     * 新增用户信息
     * 
     * @param sysUser 用户信息
     * @return 结果
     */
    public int insertSysUser(SysUser sysUser);

    /**
     * 修改用户信息
     * 
     * @param sysUser 用户信息
     * @return 结果
     */
    public int updateSysUser(SysUser sysUser);

    /**
     * 批量删除用户信息
     * 
     * @param userIds 需要删除的用户信息主键集合
     * @return 结果
     */
    public int deleteSysUserByUserIds(Long[] userIds);

    /**
     * 删除用户信息信息
     * 
     * @param userId 用户信息主键
     * @return 结果
     */
    public int deleteSysUserByUserId(Long userId);

    /**
     * 查询用户信息
     *
     * @param userName 用户名
     * @return 用户信息
     */
    SysUser selectSysUserByUserName(String userName);

    /**
     * 校验用户名称是否唯一
     *
     * @param sysUser 用户信息
     * @return 结果
     */
    boolean checkUserNameUnique(SysUser sysUser);
}
