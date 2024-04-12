package com.rain.shiro.project.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.rain.shiro.commons.core.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 当前在线用户 sys_user_online
 */
@Data
@ApiModel(value = "当前在线用户对象")
public class SysUserOnline extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户会话id")
    private String sessionId;

    @ApiModelProperty(value = "登录名称")
    private String userName;

    @ApiModelProperty(value = "登录IP地址")
    private String ip;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "session创建时间")
    private Date startTimestamp;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "session最后访问时间")
    private Date lastAccessTime;

    @ApiModelProperty(value = "超时时间，单位为分钟")
    private Long expireTime;
}
