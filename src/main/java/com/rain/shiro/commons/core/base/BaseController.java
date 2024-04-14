package com.rain.shiro.commons.core.base;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rain.shiro.commons.enums.ResultEnum;
import com.rain.shiro.commons.core.result.Result;
import com.rain.shiro.commons.utils.page.PageDomain;
import com.rain.shiro.commons.utils.page.PageUtils;
import com.rain.shiro.commons.utils.page.TableDataInfo;
import com.rain.shiro.commons.utils.page.TableSupport;
import com.rain.shiro.commons.utils.DateUtils;
import com.rain.shiro.commons.utils.SqlUtil;
import com.rain.shiro.commons.utils.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * web层通用数据处理
 */
public class BaseController {

    /**
     * 将前台传递过来的日期格式的字符串，自动转化为Date类型
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Date 类型转换
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(DateUtils.parseDate(text));
            }
        });
    }

    /**
     * 设置请求分页数据
     */
    protected void startPage() {
        PageUtils.startPage();
    }

    /**
     * 设置请求排序数据
     */
    protected void startOrderBy() {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        if (StringUtils.isNotEmpty(pageDomain.getOrderBy())) {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            PageHelper.orderBy(orderBy);
        }
    }

    /**
     * 清理分页的线程变量
     */
    protected void clearPage() {
        PageUtils.clearPage();
    }

    /**
     * 响应请求分页数据
     */
    protected TableDataInfo getDataTable(List<?> list) {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(ResultEnum.SUCCESS.getCode());
        rspData.setMsg(ResultEnum.SUCCESS.getMsg());
        Map<String, Object> res = new HashMap<>();
        res.put("total", new PageInfo(list).getTotal());
        res.put("rows", list);
        rspData.setData(res);
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }

    /**
     * 返回成功信息
     */
    public Result success() {
        return Result.success();
    }

    public Result success(Object data) {
        return Result.success(data);
    }

    /**
     * 返回失败信息
     */
    public Result error() {
        return Result.error();
    }

    public Result error(String msg) {
        return Result.error(msg);
    }

    public Result error(ResultEnum resultEnum) {
        return Result.error(resultEnum);
    }


    /**
     * 返回警告信息
     */
    public Result warn() {
        return Result.warn();
    }

    /**
     * 返回结果
     *
     * @param row 行数
     */
    public Result isSuccess(int row) {
        return row > 0 ? success() : error();
    }


    /**
     * 页面跳转
     */
    public String redirect(String url) {
        return StringUtils.format("redirect:{}", url);
    }

    /**
     * 获取用户缓存信息 todo
     */
//    public LoginUser getLoginUser() {
//        return SecurityUtils.getLoginUser();
//    }
//
//    /**
//     * 获取登录用户id
//     */
//    public Long getUserId() {
//        return getLoginUser().getUserId();
//    }
//
//    /**
//     * 获取登录部门id
//     */
//    public Long getDeptId() {
//        return getLoginUser().getDeptId();
//    }
//
//    /**
//     * 获取登录用户名
//     */
//    public String getUsername() {
//        return getLoginUser().getUsername();
//    }
}
