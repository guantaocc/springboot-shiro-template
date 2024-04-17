package com.rain.shiro.project.entity;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.rain.shiro.commons.core.base.BaseEntity;

/**
 * 订单对象 sys_order
 *
 * @author ruoyi
 * @date 2024-04-17
 */
@Data
@ApiModel(value = "订单")
public class SysOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 预约手机号 */
    @ApiModelProperty(value = "预约手机号")
    private String phone;

    /** 预约机构地址 */
    @ApiModelProperty(value = "预约机构地址")
    private String address;

    /** 预约机构电话 */
    @ApiModelProperty(value = "预约机构电话")
    private String institutionPhone;

    /** 套餐id */
    @ApiModelProperty(value = "套餐id")
    private String packageId;

    /** 订单用户id */
    @ApiModelProperty(value = "订单用户id")
    private Long userId;

    /** 是否支付（0未支付 1未支付 2已取消） */
    @ApiModelProperty(value = "是否支付")
    private String orderStatus;

    /** 报告下载地址 */
    @ApiModelProperty(value = "报告下载地址")
    private String reportUrl;

}
