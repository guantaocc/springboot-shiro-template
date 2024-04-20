package com.rain.shiro.project.entity;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.rain.shiro.commons.core.base.BaseEntity;

/**
 * 资讯对象 sys_banner
 *
 * @author ruoyi
 * @date 2024-04-20
 */
@Data
@ApiModel(value = "资讯")
public class SysBanner extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** banner编号 */
    private Long id;

    /** banner标题 */
    @ApiModelProperty(value = "banner标题")
    private String name;

    /** 分类简介 */
    @ApiModelProperty(value = "分类简介")
    private String picture;

}
