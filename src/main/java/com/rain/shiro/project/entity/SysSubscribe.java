package com.rain.shiro.project.entity;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.rain.shiro.commons.core.base.BaseEntity;

/**
 * 预约对象 sys_subscribe
 *
 * @author ruoyi
 * @date 2024-04-19
 */
@Data
@ApiModel(value = "预约")
public class SysSubscribe extends BaseEntity
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

    /** 套餐名称 */
    @ApiModelProperty(value = "套餐名称")
    private String packageName;

    /** 订单用户id */
    @ApiModelProperty(value = "预约用户id")
    private Long userId;

    /** 订单支付人 */
    @ApiModelProperty(value = "预约人")
    private String userName;

    /** 是否支付（已支付） */
    @ApiModelProperty(value = "预约状态")
    private String orderStatus;

    /** 报告下载地址 */
    @ApiModelProperty(value = "报告下载地址")
    private String reportUrl;

}
