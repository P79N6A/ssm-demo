package com.demo;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 数组转arrayList
 *
 * @author fangcong
 */
public class ArrayConvertsToArrayList {

    public static void main(String[] args) {
        String[] str = {"abc", "bbf", "ccd", "eed"};
        ArrayList<String> list = new ArrayList<>(Arrays.asList(str));
        System.out.println("size=" + list.size());
        for (String str1 : list) {
            System.out.print(str1 + "\t");
        }
    }
}
