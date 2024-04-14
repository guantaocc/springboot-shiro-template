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
@ApiModel(value = "系统分类")
public class SysCategory extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "分类ID")
    private Long id;

    @NotBlank(message = "分类名称不能为空")
    @ApiModelProperty(value = "分类名称")
    private String name;

    @ApiModelProperty(value = "分类简介")
    private String info;
}
