package com.rain.shiro.commons.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 用户状态
 */
@Getter
@AllArgsConstructor
public enum UserStatus {
    OK("0", "正常"),
    DISABLE("1", "停用");

    private final String code;
    private final String info;
}
