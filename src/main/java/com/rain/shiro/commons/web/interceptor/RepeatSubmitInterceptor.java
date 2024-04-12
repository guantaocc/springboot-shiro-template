package com.rain.shiro.commons.web.interceptor;

import com.alibaba.fastjson.JSON;
import com.rain.shiro.commons.core.result.Result;
import com.rain.shiro.commons.utils.ServletUtils;
import com.rain.shiro.commons.annotations.RepeatSubmit;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * 防止重复提交拦截器
 */
@Component
public abstract class RepeatSubmitInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            RepeatSubmit annotation = method.getAnnotation(RepeatSubmit.class);

            if (annotation != null && this.isRepeatSubmit(request, annotation)) {
                Result ajaxResult = Result.error(annotation.message());
                ServletUtils.renderString(response, JSON.toJSONString(ajaxResult));
                return false;
            }
        }

        return true;
    }

    /**
     * 验证是否重复提交由子类实现具体的防重复提交的规则
     *
     * @param request 请求信息
     * @param annotation 防重复注解参数
     * @return 结果
     * @throws Exception
     */
    public abstract boolean isRepeatSubmit(HttpServletRequest request, RepeatSubmit annotation) throws IOException;
}
