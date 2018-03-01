package com.fc.common;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author fangcong on 2018/2/8.
 */
public class SqlUtils {

    /**
     * in查询时按1000长度拆分，myBatis双重for循环
     * @param items 集合迭代
     * @param <T>   泛型
     * @return
     */
    public static <T> List<Set<T>> split(Iterable<T> items) {
        List<Set<T>> result = new ArrayList<>();
        if (null == items) {
            return result;
        }

        Set<T> tempSet = new HashSet<>();
        for (T item : items) {
            if (null == item) {
                continue;
            }
            tempSet.add(item);
        }

        Set<T> set = new HashSet<>();
        result.add(set);
        for (T item : tempSet) {
            if (set.size() == 1000) {
                set = new HashSet<>();
                result.add(set);
            }

            if (null == item) {
                continue;
            }

            set.add(item);
        }
        return result;
    }

}
