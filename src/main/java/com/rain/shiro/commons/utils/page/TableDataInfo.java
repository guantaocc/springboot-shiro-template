package com.rain.shiro.commons.utils.page;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 表格分页数据对象
 */
@Data
@ApiModel("表格分页数据模型")
public class TableDataInfo<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "总记录数")
    private long total;

    @ApiModelProperty(value = "列表数据")
    private List<T> data;

    @ApiModelProperty(value = "消息状态码")
    private int code;

    @ApiModelProperty(value = "消息内容")
    private String msg;
}
