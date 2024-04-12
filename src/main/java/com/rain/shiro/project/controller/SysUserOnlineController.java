package com.rain.shiro.project.controller;

import com.rain.shiro.commons.core.base.BaseController;
import com.rain.shiro.commons.enums.ResultEnum;
import com.rain.shiro.commons.core.result.Result;
import com.rain.shiro.commons.utils.StringUtils;
import com.rain.shiro.commons.utils.page.TableDataInfo;
import com.rain.shiro.commons.utils.spring.SpringUtils;
import com.rain.shiro.commons.constant.ShiroConstants;
import com.rain.shiro.commons.utils.ShiroUtils;
import com.rain.shiro.project.entity.SysUser;
import com.rain.shiro.project.entity.SysUserOnline;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.*;

/**
 * 在线用户监控
 */
@Api(tags = "在线用户监控")
@Controller
@RequestMapping("/online")
public class SysUserOnlineController extends BaseController {

    @Autowired
    private RedisSessionDAO redisSessionDAO;

    @ApiOperation(value = "在线用户列表")
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo<SysUserOnline> list(SysUserOnline userOnline) {
        String ip = userOnline.getIp();
        String userName = userOnline.getUserName();
        TableDataInfo rspData = new TableDataInfo();
        Collection<Session> sessions = redisSessionDAO.getActiveSessions();
        Iterator<Session> it = sessions.iterator();
        List<SysUserOnline> sessionList = new ArrayList<>();
        while (it.hasNext()) {
            SysUserOnline user = getSession(it.next());
            if (StringUtils.isNotNull(user)) {
                if (StringUtils.isNotEmpty(ip) && StringUtils.isNotEmpty(userName)) {
                    if (StringUtils.equals(ip, user.getIp()) && StringUtils.equals(userName, user.getUserName())) {
                        sessionList.add(user);
                    }
                } else if (StringUtils.isNotEmpty(ip)) {
                    if (StringUtils.equals(ip, user.getIp())) {
                        sessionList.add(user);
                    }
                } else if (StringUtils.isNotEmpty(userName)) {
                    if (StringUtils.equals(userName, user.getUserName())) {
                        sessionList.add(user);
                    }
                } else {
                    sessionList.add(user);
                }
            }
        }
        rspData.setData(sessionList);
        rspData.setTotal(sessionList.size());
        rspData.setCode(200);
        return rspData;
    }

    @ApiOperation(value = "强退用户", notes = "{\"userName\":\"admin\",\"sessionId\":\"d267e027-2d43-487a-af1e-73e6331749e1\"}")
    @PostMapping("/forceLogout")
    @ResponseBody
    public Result batchForceLogout(@RequestBody SysUserOnline sysUserOnline) {
        String sessionId = sysUserOnline.getSessionId();
        String userName = sysUserOnline.getUserName();
        if (sessionId.equals(ShiroUtils.getSessionId())) {
            return error(ResultEnum.CURRENT_USER_FORCE_LOGOUT);
        }
        redisSessionDAO.delete(redisSessionDAO.readSession(sessionId));
        removeUserCache(userName, sessionId);
        return success();
    }

    private SysUserOnline getSession(Session session) {
        Object obj = session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
        if (null == obj) {
            return null;
        }
        if (obj instanceof SimplePrincipalCollection) {
            SimplePrincipalCollection spc = (SimplePrincipalCollection) obj;
            obj = spc.getPrimaryPrincipal();
            if (null != obj && obj instanceof SysUser) {
                SysUser sysUser = (SysUser) obj;
                SysUserOnline userOnline = new SysUserOnline();
                userOnline.setSessionId(session.getId().toString());
                userOnline.setUserName(sysUser.getUserName());
                userOnline.setIp(session.getHost());
                userOnline.setStartTimestamp(session.getStartTimestamp());
                userOnline.setLastAccessTime(session.getLastAccessTime());
                userOnline.setExpireTime(session.getTimeout());
                return userOnline;
            }
        }
        return null;
    }

    public void removeUserCache(String userName, String sessionId) {
        Cache<String, Deque<Serializable>> cache = SpringUtils.getBean(RedisCacheManager.class).getCache(ShiroConstants.SYS_USER_CACHE);
        Deque<Serializable> deque = cache.get(userName);
        if (StringUtils.isEmpty(deque) || deque.size() == 0) {
            return;
        }
        deque.remove(sessionId);
        cache.put(userName, deque);
    }
}
