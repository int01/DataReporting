package com.cap.datareporting.common.utils;

import com.cap.datareporting.entity.SysUser;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Shiro工具类
 */
public class ShiroUtil {

    /**
     * 加密算法
     */
    public final static String HASH_ALGORITHM_NAME = EncryptUtil.HASH_ALGORITHM_NAME;

    /**
     * 循环次数
     */
    public final static int HASH_ITERATIONS = EncryptUtil.HASH_ITERATIONS;

    /**
     * 加密处理（64位字符）
     * 备注：采用自定义的密码加密方式，其原理与SimpleHash一致，
     * 为的是在多个模块间可以使用同一套加密方式，方便共用系统用户。
     *
     * @param password 密码
     * @param salt     密码盐
     */
    public static String encrypt(String password, String salt) {
        return EncryptUtil.encrypt(password, salt, HASH_ALGORITHM_NAME, HASH_ITERATIONS);
    }

    /**
     * 获取随机盐值
     */
    public static String getRandomSalt() {
        return EncryptUtil.getRandomSalt();
    }


    /**
     * 获取用户IP地址
     */
    public static String getIp() {
        HttpServletRequest request = HttpServletUtil.getRequest();

        // 反向代理时获取真实ip
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 获取当前用户对象
     */
    public static SysUser getSubject() {
        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();

//        // 初始化延迟加载的部门信息
//        if (user != null && !Hibernate.isInitialized(user.getDept())) {
//            try {
//                Hibernate.initialize(user.getDept());
//            } catch (LazyInitializationException e) {
//                // 部门数据延迟加载超时，重新查询用户数据（用于更新“记住我”状态登录的数据）
//                UserService userService = SpringContextUtil.getBean(UserService.class);
//                User reload = userService.getById(user.getId());
//                Hibernate.initialize(reload.getDept());
//                // 将重载用户数据拷贝到登录用户中
//                BeanUtils.copyProperties(reload, user, "roles");
//            }
//        }

        return user;
    }

}
