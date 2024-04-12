package com.rain.shiro.commons.core.result;

import com.rain.shiro.commons.enums.ResultEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 返回数据模型
 */
@ApiModel("返回数据模型")
@Data
public class Result<T> {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("状态码")
    private int code;

    @ApiModelProperty("返回信息")
    private String msg;

    @ApiModelProperty("返回数据")
    private T data;

    private Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 返回成功信息
     */
    public static <T> Result<T> success() {
        return success(ResultEnum.SUCCESS);
    }

    public static <T> Result<T> success(T data) {
        return success(ResultEnum.SUCCESS, data);
    }

    public static <T> Result<T> success(String msg) {
        return success(msg, null);
    }

    public static <T> Result<T> success(String msg, T data) {
        return new Result<>(ResultEnum.SUCCESS.getCode(), msg, data);
    }

    public static <T> Result<T> success(ResultEnum resultEnum) {
        return success(resultEnum, null);
    }

    public static <T> Result<T> success(ResultEnum resultEnum, T data) {
        return new Result<>(resultEnum.getCode(), resultEnum.getMsg(), data);
    }

    /**
     * 返回错误信息
     */
    public static <T> Result<T> error() {
        return error(ResultEnum.ERROR);
    }

    public static <T> Result<T> error(String msg) {
        return error(msg, null);
    }

    public static <T> Result<T> error(String msg, T data) {
        return new Result<>(ResultEnum.ERROR.getCode(), msg, data);
    }

    public static <T> Result<T> error(int code, String msg) {
        return new Result<>(code, msg, null);
    }

    public static <T> Result<T> error(ResultEnum resultEnum) {
        return error(resultEnum, null);
    }

    public static <T> Result<T> error(ResultEnum resultEnum, T data) {
        return new Result<>(resultEnum.getCode(), resultEnum.getMsg(), data);
    }

    /**
     * 返回警告信息
     */
    public static <T> Result<T> warn() {
        return warn(ResultEnum.WARN);
    }

    public static <T> Result<T> warn(String msg) {
        return warn(msg, null);
    }

    public static <T> Result<T> warn(String msg, T data) {
        return new Result<>(ResultEnum.WARN.getCode(), msg, data);
    }

    public static <T> Result<T> warn(int code, String msg) {
        return new Result<>(code, msg, null);
    }

    public static <T> Result<T> warn(ResultEnum resultEnum) {
        return warn(resultEnum, null);
    }

    public static <T> Result<T> warn(ResultEnum resultEnum, T data) {
        return new Result<>(resultEnum.getCode(), resultEnum.getMsg(), data);
    }
}
