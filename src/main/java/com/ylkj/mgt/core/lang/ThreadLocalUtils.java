package com.ylkj.mgt.core.lang;

/**
 * @Description: 线程变量
 * @Date: 2019-04-02 10:25:56
 */
public class ThreadLocalUtils {

    private static ThreadLocal<ThreadEntity> holder = new ThreadLocal<>();

    public static ThreadEntity get() {
        return holder.get();
    }

    private static ThreadEntity getOrCreate() {
        ThreadEntity entity = holder.get();
        if (entity == null) {
            entity = new ThreadEntity();
            set(entity);
        }
        return entity;
    }

    public static void set(ThreadEntity value) {
        holder.set(value);
    }

    public static void clear() {
        holder.remove();
    }

    public static String getLogId() {
        ThreadEntity entity = get();
        if (entity != null) {
            return entity.getLogId();
        }
        return null;
    }

    public static void setLogId(String logId) {
        ThreadEntity entity = getOrCreate();
        entity.setLogId(logId);
    }

}
