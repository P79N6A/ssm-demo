package com.jvm.gc;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fangcong on 2018/7/27.
 */
public class Test {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        while (true) {
            list.add("test");
        }
    }
}
