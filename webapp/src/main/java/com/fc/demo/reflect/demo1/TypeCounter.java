package com.fc.demo.reflect.demo1;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by fangcong on 2017/4/7.
 */
public class TypeCounter extends HashMap<Class<?>, Integer> {

    private Class<?> baseType;

    public TypeCounter(Class<?> baseType) {
        this.baseType = baseType;
    }

    public void count(Object obj) {
        Class<?> type = obj.getClass();
        if (!baseType.isAssignableFrom(type)) {
            throw new RuntimeException(
                    obj + " incorrect type " + type + ", should be type or subtype of " + baseType);
        }
        countClass(type);
    }

    public void countClass(Class<?> type) {
        Integer quantity = get(type);
        put(type, quantity == null ? 1 : quantity + 1);
        Class<?> supperClass = type.getSuperclass();
        if (supperClass != null && baseType.isAssignableFrom(supperClass)) {
            countClass(supperClass);
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("{");
        for (Map.Entry<Class<?>, Integer> pair : entrySet()) {
            result.append(pair.getKey().getSimpleName());
            result.append("=");
            result.append(pair.getValue());
            result.append(", ");
        }
        result.delete(result.length() - 2, result.length());
        result.append("} ");
        return result.toString();
    }
}
