package com.sgq.shortlink.project.common.constant;

/**
 * Redis Key 常量类
 */
public class RedisKeyConstant {

    /**
     * 短链接跳转前缀
     */
    public static final String GOTO_SHORT_LINK_KEY = "shortlink_goto_%S";

    /**
     * 短链接跳转锁前缀
     */
    public static final String LOCK_GOTO_SHORT_LINK_KEY = "shortlink_lock_goto_%S";
}
