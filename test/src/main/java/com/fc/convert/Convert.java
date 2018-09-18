package com.fc.convert;

import com.google.common.base.Predicate;

/**
 * @author fangcong on 2018/9/17.
 */
@FunctionalInterface
public interface Convert<T, F> {

    T convert(F form);

    default void convert() {
        System.out.println("有且仅有一个抽象方法声明");
        Convert<Integer, String> convert = form -> Integer.parseInt(form);
        Convert<String, Integer> convert1 = String::valueOf;
        Integer a = convert.convert("123");
        System.out.println(a);

        Predicate<String> predicate = s -> s.length() > 0;
        boolean b = predicate.apply("123");

    }
}
