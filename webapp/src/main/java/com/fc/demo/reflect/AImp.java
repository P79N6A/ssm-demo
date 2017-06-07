package com.fc.demo.reflect;

/**
 * Created by fangcong on 2017/4/7.
 */
public class AImp implements A {

    @Override
    public void f() {
        System.out.println("public c.f()");
    }

    public void g() {
        System.out.println("public c.g()");
    }

    protected void v() {
        System.out.println("protected c.v()");
    }

    void u() {
        System.out.println("package c.u()");
    }

    private void w() {
        System.out.println("private c.w()");
    }
}
