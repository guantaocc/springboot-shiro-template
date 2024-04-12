package com.rain.shiro.commons.utils.sign;

import com.rain.shiro.project.entity.SysUser;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * Md5加密方法
 *
 * @author ruoyi
 */
public class Md5Utils {
    private static final Logger log = LoggerFactory.getLogger(Md5Utils.class);

    /**
     * 密码校验
     * @param user          用户信息
     * @param newPassword   输入的密码
     * @return
     */
    public static boolean matches(SysUser user, String newPassword) {
        return user.getPassword().equals(encryptPassword(user.getUserName(), newPassword, user.getSalt()));
    }

    /**
     * 密码加密
     * @param userName 用户账号
     * @param password  密码
     * @param salt      盐加密
     * @return
     */
    public static String encryptPassword(String userName, String password, String salt) {
        return new Md5Hash(userName + password + salt).toHex();
    }

    private static byte[] md5(String s) {
        MessageDigest algorithm;
        try {
            algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(s.getBytes("UTF-8"));
            byte[] messageDigest = algorithm.digest();
            return messageDigest;
        } catch (Exception e) {
            log.error("MD5 Error...", e);
        }
        return null;
    }

    private static final String toHex(byte hash[]) {
        if (hash == null) {
            return null;
        }
        StringBuffer buf = new StringBuffer(hash.length * 2);
        int i;

        for (i = 0; i < hash.length; i++) {
            if ((hash[i] & 0xff) < 0x10) {
                buf.append("0");
            }
            buf.append(Long.toString(hash[i] & 0xff, 16));
        }
        return buf.toString();
    }

    public static String hash(String s) {
        try {
            return new String(toHex(md5(s)).getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.error("not supported charset...{}", e);
            return s;
        }
    }
}
