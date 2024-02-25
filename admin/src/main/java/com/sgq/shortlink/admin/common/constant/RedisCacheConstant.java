package com.sgq.shortlink.admin.common.constant;

/**
 * 短链接后管 Redis 缓存常量类
 */
public class RedisCacheConstant {

    /**
     * 用户注册分布式锁
     */
    public static final String LOCK_USER_REGISTER_KEY = "shortlink:lock_user-register:";

    /**
     * 分组创建分布式锁
     */
    public static final String LOCK_GROUP_CREATE_KEY = "shortlink:lock_group-create:%s";

    /**
     * 用户登陆缓存标识
     */
    public static final String USER_LOGIN_KRY = "short-link:login:";
}
