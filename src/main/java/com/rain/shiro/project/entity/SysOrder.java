package com.rain.shiro.project.entity;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.rain.shiro.commons.core.base.BaseEntity;

/**
 * 订单对象 sys_order
 *
 * @author ruoyi
 * @date 2024-04-20
 */
@Data
@ApiModel(value = "订单")
public class SysOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 订单id */
    @ApiModelProperty(value = "订单编号")
    private String orderId;

    /** 预约机构id */
    @ApiModelProperty(value = "预约机构id")
    private Long institutionId;

    /** 预约id */
    @ApiModelProperty(value = "预约id")
    private Long subscribeId;

    /** 套餐id */
    @ApiModelProperty(value = "套餐id")
    private Long packageId;

    /** 订单用户id */
    @ApiModelProperty(value = "订单用户id")
    private Long userId;

    /** 生成报告地址 */
    @ApiModelProperty(value = "生成报告地址")
    private String reportUrl;

}
