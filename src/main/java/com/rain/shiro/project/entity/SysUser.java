package com.rain.shiro.project.entity;

import com.rain.shiro.commons.core.base.BaseEntity;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * 用户信息对象 sys_user
 */
@Data
@ApiModel(value = "用户信息对象")
public class SysUser extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @NotBlank(message = "用户账号不能为空")
    @ApiModelProperty(value = "用户账号")
    private String userName;

    @NotBlank(message = "用户昵称不能为空")
    @ApiModelProperty(value = "用户昵称")
    private String nickName;

    @NotBlank(message = "密码不能为空")
    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "盐加密")
    private String salt;

    @ApiModelProperty(value = "帐号状态（0正常 1停用）")
    private String status;

    /**
     * 判断该用户是否为管理员
     */
    public boolean isAdmin() {
        return userId != null && userId == 1L;
    }
}
