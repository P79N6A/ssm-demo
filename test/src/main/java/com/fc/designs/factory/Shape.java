package com.fc.designs.factory;

/**
 * @author fangcong on 2018/4/11.
 */
public interface Shape {

    /**
     * 定义工厂方法
     */
    void draw();
}

class Rectangle implements Shape {

    @Override
    public void draw() {
        System.out.println("rectangle::draw");
    }
}

class Square implements Shape {

    @Override
    public void draw() {
        System.out.println("square::draw");
    }
}
