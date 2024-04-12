package com.rain.shiro.commons.utils.page;

import com.rain.shiro.commons.utils.StringUtils;
import lombok.Data;

/**
 * 分页数据
 */
@Data
public class PageDomain {
    /**
     * 当前记录起始索引
     */
    private Integer pageNum;

    /**
     * 每页显示记录数
     */
    private Integer pageSize;

    /**
     * 排序列
     */
    private String orderByColumn;

    /**
     * 排序的方向desc或者asc
     */
    private String isAsc;

    /**
     * 分页参数合理化
     */
    private Boolean reasonable;

    public String getOrderBy() {
        if (StringUtils.isEmpty(orderByColumn)) {
            // 如果排序参数为空，则使用第一列（一般为主键）倒序查询
            return "1 desc";
        }
        return StringUtils.toUnderScoreCase(orderByColumn) + " " + isAsc;
    }
}
