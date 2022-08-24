package com.qiqi.rs.admin.core.runtime.threadlocal;


import com.qiqi.rs.admin.platform.user.model.CachedUser;

/**
 * @author G
 */
public class UserContextThreadLocalHolder {

    private static ThreadLocal<CachedUser> threadLocal = new ThreadLocal<CachedUser>();

    public static CachedUser get() {
        return threadLocal.get();
    }

    public static void set(CachedUser user) {
        threadLocal.set(user);
    }

    public static void remove() {
        threadLocal.remove();
    }
}
