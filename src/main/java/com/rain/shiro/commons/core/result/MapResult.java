package com.rain.shiro.commons.core.result;

import com.rain.shiro.commons.enums.ResultEnum;

import java.util.HashMap;
import java.util.Objects;

/**
 * 返回数据模型
 */
public class MapResult extends HashMap<String, Object> {

    private static final long serialVersionUID = 1L;

    public static final String CODE_TAG = "code", MSG_TAG = "msg", DATA_TAG = "data";

    public MapResult() {}

    public MapResult(int code, String msg) {
        this.put(CODE_TAG, code);
        this.put(MSG_TAG, msg);
    }

    public MapResult(int code, String msg, Object data) {
        this.put(CODE_TAG, code);
        this.put(MSG_TAG, msg);
        this.put(DATA_TAG, data);
    }

    /**
     * 返回成功信息
     */
    public static MapResult success() {
        return success(ResultEnum.SUCCESS);
    }

    public static MapResult success(Object data) {
        return success(ResultEnum.SUCCESS, data);
    }

    public static MapResult success(String msg) {
        return success(ResultEnum.SUCCESS.getCode(), msg);
    }

    public static MapResult success(String msg, Object data) {
        return success(ResultEnum.SUCCESS.getCode(), msg, data);
    }

    public static MapResult success(ResultEnum resultEnum) {
        return success(resultEnum.getCode(), resultEnum.getMsg());
    }

    public static MapResult success(ResultEnum resultEnum, Object data) {
        return success(resultEnum.getCode(), resultEnum.getMsg(), data);
    }

    public static MapResult success(int code, String msg) {
        return new MapResult(code, msg);
    }

    public static MapResult success(int code, String msg, Object data) {
        return new MapResult(code, msg, data);
    }

    /**
     * 返回错误信息
     */
    public static MapResult error() {
        return new MapResult(ResultEnum.ERROR.getCode(), ResultEnum.ERROR.getMsg());
    }

    public static MapResult error(String msg) {
        return new MapResult(ResultEnum.ERROR.getCode(), msg);
    }

    public static MapResult error(String msg, Object data) {
        return new MapResult(ResultEnum.ERROR.getCode(), msg, data);
    }

    public static MapResult error(int code, String msg) {
        return new MapResult(code, msg);
    }

    public boolean isSuccess() {
        return Objects.equals(ResultEnum.SUCCESS.getCode(), this.get(CODE_TAG));
    }

    public boolean isWarn() {
        return Objects.equals(ResultEnum.WARN.getCode(), this.get(CODE_TAG));
    }

    public boolean isError() {
        return Objects.equals(ResultEnum.ERROR.getCode(), this.get(CODE_TAG));
    }

    /**
     * 返回警告信息
     */
    public static MapResult warn(String msg) {
        return new MapResult(ResultEnum.WARN.getCode(), msg);
    }

    public static MapResult warn(String msg, Object data) {
        return new MapResult(ResultEnum.WARN.getCode(), msg, data);
    }

    @Override
    public MapResult put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
