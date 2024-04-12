package com.rain.shiro.commons.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 返回数据枚举类
 */
@Getter
@AllArgsConstructor
public enum ResultEnum {
    /**
     * 操作成功
     */
    SUCCESS(200, "操作成功"),


    /**
     * 操作失败
     */
    USERNAME_OR_PASSWORD_WRONG(400, "用户名或密码错误"),
    UNAUTHORIZED(401, "未登录或登录超时，请重新登录"),
    FORBIDDEN(403, "没有权限，请联系管理员授权"),
    CONFLICT(409, "资源冲突，或者资源被锁"),
    UNSUPPORTED_TYPE(415, "不支持的数据，媒体类型"),
    KICK_OUT(441, "您已在别处登录，请您修改密码或重新登录"),
    PARAM_TYPE(442, "请检查参数类型是否正确"),
    CURRENT_USER_FORCE_LOGOUT(443, "当前登录用户无法强退"),
    ERROR(500, "操作失败"),
    NOT_IMPLEMENTED(501, "接口未实现"),

    /**
     * 操作警告
     */
    WARN(601, "操作警告");

    private final Integer code;
    private final String msg;
}
