package com.fc.designs.delegate;

/**
 * 测试委派模式
 *
 * @author fangcong on 2018/7/30.
 */
public class TestDelegate {

    public static void main(String[] args) {
        DelegateObject object = new DelegateObject("张三");
        object.handleCredicate();
    }
}
