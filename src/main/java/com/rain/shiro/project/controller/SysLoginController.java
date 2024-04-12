package com.rain.shiro.project.controller;

import com.google.code.kaptcha.Producer;
import com.rain.shiro.commons.core.base.BaseController;
import com.rain.shiro.commons.core.result.MapResult;
import com.rain.shiro.commons.core.result.Result;
import com.rain.shiro.commons.utils.sign.Base64;
import com.rain.shiro.commons.utils.sign.RsaUtils;
import com.rain.shiro.commons.utils.uuid.IdUtils;
import com.rain.shiro.framework.redis.RedisCache;
import com.rain.shiro.commons.constant.ShiroConstants;
import com.rain.shiro.project.entity.po.LoginParam;
import com.rain.shiro.project.service.impl.SysLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Api(tags = "登录接口")
@Slf4j
@RestController
public class SysLoginController extends BaseController {

    @Resource(name = "captchaProducer")
    private Producer captchaProducer;

    @Resource(name = "captchaProducerMath")
    private Producer captchaProducerMath;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private SysLoginService sysLoginService;

    @ApiOperation(value = "用户登录", notes = "{\n" +
            "  \"code\": \"ad4p\",\n" +
            "  \"password\": \"th67QMCowWgdIwRZvN2b5/GmNhZ/ODWNKP+kbhfQ6/NUHOnPkTM6w13d65ykBXMMMkyhLwmW/4Mr8BLdO4gZOA==\",\n" +
            "  \"username\": \"admin\",\n" +
            "  \"uuid\": \"8c5b4f8f69a54f4b96096ada3088945f\"\n" +
            "}")
    @PostMapping("/login")
    public Result login(@Validated @RequestBody LoginParam loginParam) {
        // 验证码校验
        sysLoginService.captchaValidate(loginParam.getCode(), loginParam.getUuid());
        // 用户信息验证
        Subject subject = SecurityUtils.getSubject();
        String username = loginParam.getUsername();
        String password = RsaUtils.decryptByPrivateKey(loginParam.getPassword());
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, true);
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            log.error("用户[" + username + "]未通过登录验证.", e);
            return error(e.getMessage());
        }
        return success(subject.getSession().getId());
    }

    @ApiOperation(value = "用户信息", notes = "用户信息")
    @GetMapping("/info")
    public Result info() {
        Map<String, Object> map = new HashMap<>();
        map.put("roles", new ArrayList<>());
        map.put("introduction", "I am a super admin HaHaHaHa~");
        map.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        map.put("name", "rainbow.pig");
        return success(map);
    }

    @ApiOperation(value = "用户退出", notes = "用户退出")
    @PostMapping("/logout")
    public Result logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return success();
    }

    @ApiOperation(value = "验证码")
    @GetMapping(value = "/captcha")
    public MapResult getCode() {
        MapResult result = MapResult.success();
        // 保存验证码信息
        String uuid = IdUtils.simpleUUID();
        String verifyKey = ShiroConstants.SYS_CAPTCHA + uuid;
        // 生成验证码
        String code = captchaProducer.createText();
        log.info("captcha：" + code);
        BufferedImage image = captchaProducer.createImage(code);
        redisCache.setCacheObject(verifyKey, code, ShiroConstants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
        // 转换流信息写出
        try (FastByteArrayOutputStream os = new FastByteArrayOutputStream()) {
            ImageIO.write(image, "jpg", os);
            return result.put("uuid", uuid).put("img", Base64.encode(os.toByteArray()));
        } catch (IOException e) {
            log.error("生成验证码异常", e);
            return MapResult.error(e.getMessage());
        }
    }

}
