package com.rain.shiro.project.service.impl;

import com.rain.shiro.commons.exception.user.*;
import com.rain.shiro.commons.utils.StringUtils;
import com.rain.shiro.commons.utils.sign.Md5Utils;
import com.rain.shiro.framework.redis.RedisCache;
import com.rain.shiro.commons.constant.ShiroConstants;
import com.rain.shiro.commons.enums.UserStatus;
import com.rain.shiro.project.entity.SysUser;
import com.rain.shiro.project.service.SysUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 登录校验方法
 */
@Component
public class SysLoginService {

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private SysUserService sysUserService;

    /**
     * 密码错误重试次数
     */
    @Value(value = "${shiro.user.password.maxRetryCount}")
    private Integer maxRetryCount;

    /**
     * 登录校验
     */
    public SysUser login(String username, String password) {
        SysUser sysUser = sysUserService.selectSysUserByUserName(username);
        if (sysUser == null) {
            throw new AuthenticationException();
        }
        if (UserStatus.DISABLE.getCode().equals(sysUser.getStatus())) {
            throw new UserBlockedException();
        }
        // 密码校验
        passwordValidate(sysUser, password);
        return sysUser;
    }

    /**
     * 密码校验
     */
    private void passwordValidate(SysUser sysUser, String password) {
        String userName = sysUser.getUserName();
        // 密码错误次数校验
        Integer retryCount = redisCache.getCacheObject(getCacheKey(userName));
        if (retryCount == null) {
            retryCount = 0;
            redisCache.setCacheObject(getCacheKey(userName), retryCount, 10, TimeUnit.MINUTES);
        }
        if (retryCount >= maxRetryCount) {
            throw new UserPasswordRetryLimitExceedException(maxRetryCount);
        }
        // 密码校验
        if (!Md5Utils.matches(sysUser, password)) {
            retryCount += 1;
            redisCache.setCacheObject(getCacheKey(userName), retryCount, 10, TimeUnit.MINUTES);
            throw new UserPasswordNotMatchException();
        }else {
            clearLoginRecordCache(userName);
        }
    }

    /**
     * 验证码校验
     */
    public void captchaValidate(String code, String uuid) {
        String key = ShiroConstants.SYS_CAPTCHA + uuid;
        String captcha = redisCache.getCacheObject(key);
        redisCache.deleteObject(key);
        if (captcha == null) {
            throw new CaptchaExpireException();
        }
        if (!StringUtils.equalsIgnoreCase(captcha, code)) {
            throw new CaptchaException();
        }
    }

    private void clearLoginRecordCache(String userName) {
        redisCache.deleteObject(getCacheKey(userName));
    }

    /**
     * 设置cache key
     *
     * @param userName 用户名
     * @return 缓存键key
     */
    private String getCacheKey(String userName) {
        return ShiroConstants.SYS_RETRY_COUNT + userName;
    }
}
