package com.rain.shiro.commons.constant;

/**
 * Shiro通用常量
 */
public class ShiroConstants {

    /**
     * 系统活跃用户缓存 key
     */
    public static final String SYS_USER_CACHE = "sys-userCache";

    /**
     * 验证码 key
     */
    public static final String SYS_CAPTCHA = "sys_captcha:";

    /**
     * 验证码有效期（分钟）
     */
    public static final Integer CAPTCHA_EXPIRATION = 2;

    /**
     * 令牌自定义标识
     */
    public static final String AUTHORIZATION = "token";

    /**
     * 密码错误次数 key
     */
    public static final String SYS_RETRY_COUNT = "sys_retryCount:";
}
