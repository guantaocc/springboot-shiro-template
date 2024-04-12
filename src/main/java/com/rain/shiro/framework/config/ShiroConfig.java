package com.rain.shiro.framework.config;

import com.rain.shiro.commons.constant.Constants;
import com.rain.shiro.commons.utils.StringUtils;
import com.rain.shiro.commons.utils.security.CipherUtils;
import com.rain.shiro.framework.redis.FastJson2JsonRedisSerializer;
import com.rain.shiro.framework.shiro.LoginFilter;
import com.rain.shiro.framework.shiro.MySessionManager;
import com.rain.shiro.framework.shiro.UserRealm;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;

/**
 * shiro安全框架 配置类
 */
@Configuration
public class ShiroConfig {

    /**
     * Session超时时间，单位为毫秒（默认30分钟）
     */
    @Value("${shiro.session.expireTime}")
    private int expireTime;

    /**
     * 同一个用户最大会话数
     */
    @Value("${shiro.session.maxSession}")
    private int maxSession;

    /**
     * 踢出之前登录的/之后登录的用户，默认false踢出之前登录的用户
     */
    @Value("${shiro.session.kickoutAfter}")
    private boolean kickoutAfter;

    /**
     * 设置Cookie的域名
     */
    @Value("${shiro.cookie.domain}")
    private String domain;

    /**
     * 设置cookie的有效访问路径
     */
    @Value("${shiro.cookie.path}")
    private String path;

    /**
     * 设置HttpOnly属性
     */
    @Value("${shiro.cookie.httpOnly}")
    private boolean httpOnly;

    /**
     * 设置Cookie的过期时间，秒为单位
     */
    @Value("${shiro.cookie.maxAge}")
    private int maxAge;

    /**
     * 设置cipherKey密钥
     */
    @Value("${shiro.cookie.cipherKey}")
    private String cipherKey;

    /**
     * 是否开启记住我功能
     */
    @Value("${shiro.rememberMe.enabled}")
    private boolean rememberMe;

    /**
     * redis缓存地址
     */
    @Value("${spring.redis.port}")
    private String redisPort;

    /**
     * redis缓存端口
     */
    @Value("${spring.redis.host}")
    private String redisHost;

    /**
     * redis数据库索引
     */
    @Value("${spring.redis.database}")
    private int database;

    /**
     * redis超时时间
     */
    @Value("${spring.redis.timeout}")
    private int timeout;

    /**
     * redis密码
     */
    @Value("${spring.redis.password}")
    private String password;

    /**
     * 过滤配置
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 配置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 配置拦截、开放路由
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        // 开放资源一定是在拦截资源上面，不然会造成死循环
        map.put("/doc.html", "anon");
        map.put("/v2/api-docs/**", "anon");
        map.put("/webjars/**", "anon");
        map.put("/swagger-resources/**", "anon");
        map.put("/favicon.ico", "anon");
        map.put("/login", "anon");
        map.put("/captcha", "anon");
        map.put("/test/**", "anon");

        map.put("/**", "loginFilter");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        // 配置过滤器
        LinkedHashMap<String, Filter> filterMap = new LinkedHashMap<>();
        filterMap.put("loginFilter", loginFilter());
        shiroFilterFactoryBean.setFilters(filterMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 安全管理器
     */
    @Bean
    public SecurityManager securityManager(UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置realm
        securityManager.setRealm(userRealm);
        // 记住我
        securityManager.setRememberMeManager(rememberMe ? rememberMeManager() : null);
        // 缓存管理器
        securityManager.setCacheManager(redisCacheManager());
        // 会话管理器
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }

    /**
     * 自定义域
     */
    @Bean
    public UserRealm userRealm() {
        UserRealm userRealm = new UserRealm();
        userRealm.setAuthorizationCacheName(Constants.SYS_AUTH_CACHE);
        userRealm.setCacheManager(redisCacheManager());
        return userRealm;
    }

    /**
     * 自定义过滤器
     */
    public LoginFilter loginFilter() {
        LoginFilter loginFilter = new LoginFilter();
        loginFilter.setCacheManager(redisCacheManager());
        loginFilter.setSessionManager(sessionManager());
        // 同一个用户最大的会话数，默认-1无限制；比如2的意思是同一个用户允许最多同时两个人登录
        loginFilter.setMaxSession(maxSession);
        // 是否踢出后来登录的，默认是false；即后者登录的用户踢出前者登录的用户；踢出顺序
        loginFilter.setKickOutAfter(kickoutAfter);
        return loginFilter;
    }

    /**
     * 会话管理器
     */
    @Bean
    public SessionManager sessionManager() {
        MySessionManager manager = new MySessionManager();
        // 加入缓存管理器
        manager.setCacheManager(redisCacheManager());
        // 去掉 JSESSIONID
        manager.setSessionIdUrlRewritingEnabled(false);
        // 自定义SessionDao
        manager.setSessionDAO(redisSessionDAO());
        // 设置全局session超时时间
        manager.setGlobalSessionTimeout(expireTime * 60 * 1000);
        return manager;
    }

    /**
     * 缓存管理器
     */
    @Bean
    public RedisCacheManager redisCacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        redisCacheManager.setPrincipalIdFieldName("userId");
        return redisCacheManager;
    }

    /**
     * Redis管理器
     */
    @Bean
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(redisHost + ":" + redisPort);
        redisManager.setDatabase(database);
        if (StringUtils.isNotEmpty(password)) {
            redisManager.setPassword(password);
        }
        redisManager.setTimeout(timeout);
        return redisManager;
    }
    /**
     * Redis会话管理器
     */
    @Bean
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        redisSessionDAO.setExpire(expireTime * 60);
        return redisSessionDAO;
    }

    /**
     * cookie 属性设置
     */
    public SimpleCookie rememberMeCookie() {
        SimpleCookie cookie = new SimpleCookie("rememberMe");
        cookie.setDomain(domain);
        cookie.setPath(path);
        cookie.setHttpOnly(httpOnly);
        cookie.setMaxAge(maxAge * 24 * 60 * 60);
        return cookie;
    }

    /**
     * 记住我
     */
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        if (StringUtils.isNotEmpty(cipherKey)) {
            cookieRememberMeManager.setCipherKey(Base64.decode(cipherKey));
        } else {
            cookieRememberMeManager.setCipherKey(CipherUtils.generateNewKey(128, "AES").getEncoded());
        }
        return cookieRememberMeManager;
    }

    /**
     * 开启shiro的注解 {@link RequiresRoles} 角色鉴权 {@link RequiresPermissions} 菜单鉴权
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    public static DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setUsePrefix(true);
        return defaultAdvisorAutoProxyCreator;
    }
}
