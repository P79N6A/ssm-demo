package com.fc.ehcache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by fangcong on 2016/12/5.
 */
public class MyCacheManager<T> {

    private Map<String,T> cahce = new ConcurrentHashMap<>();

    /**
     * 从缓存取值
     * @param key key值
     * @return value
     */
    public T getValue(Object key){
        return cahce.get(key);
    }

    /**
     * 增加或修改缓存值
     * @param key 键
     * @param value 值
     */
    public void addOrUpdateCache(String key,T value){
        cahce.put(key,value);
    }

    /**
     * 根据key值删除缓存值
     * @param key 键
     */
    public void deleteCache(String key){
        if (cahce.containsKey(key)){
            cahce.remove(key);
        }
    }

    /**
     * 删除所有缓存值
     */
    public void evictCache(){
        cahce.clear();
    }
}
