package com.fc.java.improve;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import com.fc.bean.Person;

/**
 * 使用clone()方法代替new创建对象
 *
 * @author fangcong
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class CloneObject {

    private static Person person = new Person();

    /**
     * clone方法创建对象示例，实体类需实现cloneable接口并重写clone()方法
     *
     * @return
     */
    public static Person createPerson() {
        return person.clone();
    }

    /**
     * hashSet实现list集合去重
     *
     * @param <E>
     * @param list
     */
    public static void removeDuplicate(List list) {
        HashSet hashSet = new HashSet(list);
        list.clear();
        list.addAll(hashSet);
    }

    /**
     * 输出list集合元素
     *
     * @param list
     */
    public static void printList(List list) {
        for (Object object : list) {
            System.out.print(object + "\t");
        }
        System.out.println();
    }

    /**
     * 测试list去重，需要初始化为ArrayList集合，否则会报错
     *
     * @param args
     */
    public static void main(String[] args) {
        List list = new ArrayList(Arrays.asList("aa", "bb", "cc", "dd", "cc", "ee", "aa"));
        printList(list);
        removeDuplicate(list);
        printList(list);
    }
}
