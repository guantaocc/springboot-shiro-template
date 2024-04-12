package com.rain.shiro.project.mapper;

import java.util.List;
import com.rain.shiro.project.entity.SysUser;

/**
 * 用户信息Mapper接口
 */
public interface SysUserMapper {
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
     * 删除用户信息
     * 
     * @param userId 用户信息主键
     * @return 结果
     */
    public int deleteSysUserByUserId(Long userId);

    /**
     * 批量删除用户信息
     * 
     * @param userIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysUserByUserIds(Long[] userIds);

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
     * @param userName 用户账号
     * @return 结果
     */
    SysUser checkUserNameUnique(String userName);
}
