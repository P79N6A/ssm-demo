package com.demo.locks;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

/**
 * 通过一个缓存示例来说明读写锁使用方式
 *
 * @author fangcong on 2018/4/24.
 */
public class ReadWriteLockCacheDemo {

    /**
     * 定义非线程安全的map，使用读写锁来保证线程安全
     */
    static Map<String, Object> map = new HashMap<>(16);

    static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    /**
     * 定义读锁
     */
    static ReadLock readLock = readWriteLock.readLock();

    /**
     * 定义写锁
     */
    static WriteLock writeLock = readWriteLock.writeLock();

    public static final Object getValue(String key) {
        readLock.lock();
        try {
            return map.get(key);
        } finally {
            readLock.unlock();
        }
    }

    public static final Object putValue(String key, Object value) {
        writeLock.lock();
        try {
            return map.put(key, value);
        } finally {
            writeLock.unlock();
        }
    }

    public static final void clear() {
        writeLock.lock();
        try {
            map.clear();
        } finally {
            writeLock.unlock();
        }
    }

}
