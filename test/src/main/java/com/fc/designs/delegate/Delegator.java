package com.fc.designs.delegate;

/**
 *
 * 委派模式——委托者
 *
 * @author fangcong on 2018/7/30.
 */
public class Delegator {

    private String name;

    public Delegator(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void handleCredicate() {
        System.out.println(name + "正在办理");
    }
}
