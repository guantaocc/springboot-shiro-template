package com.rain.shiro.project.entity;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.rain.shiro.commons.core.base.BaseEntity;

/**
 * 机构对象 sys_institution
 *
 * @author ruoyi
 * @date 2024-04-17
 */
@Data
@ApiModel(value = "机构")
public class SysInstitution extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 机构名称 */
    @ApiModelProperty(value = "机构名称")
    private String name;

    /** 机构地址 */
    @ApiModelProperty(value = "机构地址")
    private String address;

    /** 机构电话 */
    @ApiModelProperty(value = "机构电话")
    private String phone;

    /** 机构封面 */
    @ApiModelProperty(value = "机构封面")
    private String picture;

    /** 机构封面 */
    @ApiModelProperty(value = "状态")
    private String status;
}
