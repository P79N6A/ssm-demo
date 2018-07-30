package com.fc.designs.delegate;

/**
 * 被委派对象，负责委派者的职责
 *
 * @author fangcong on 2018/7/30.
 */
public class DelegateObject {

    private String name;

    private Delegator delegator;

    public DelegateObject(String name) {
        this.name = name;
        delegator = new Delegator(name);
    }

    public void handleCredicate() {
        System.out.println("接受" + name + "委托");
        delegator.handleCredicate();
    }
}
