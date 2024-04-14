package com.rain.shiro.project.entity;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.rain.shiro.commons.core.base.BaseEntity;

/**
 * 套餐对象 sys_package
 *
 * @author guantao
 * @date 2024-04-14
 */
@Data
@ApiModel(value = "套餐")
public class SysPackage extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 套餐ID
     */
    private Long id;

    /**
     * 套餐名称
     */
    @ApiModelProperty(value = "套餐名称")
    private String name;

    /**
     * 套餐价格
     */
    @ApiModelProperty(value = "套餐价格")
    private String price;

    /**
     * 检查项
     */
    @ApiModelProperty(value = "检查项")
    private String checked;

    /**
     * 所属分类
     */
    @ApiModelProperty(value = "所属分类")
    private String categoryId;

    /**
     * 描述图片
     */
    @ApiModelProperty(value = "描述图片")
    private String picture;
}

