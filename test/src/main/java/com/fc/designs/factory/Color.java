package com.fc.designs.factory;

/**
 * @author fangcong on 2018/4/11.
 */
public interface Color {

    void fill();
}

class Red implements Color {

    @Override
    public void fill() {
        System.out.println("red::fill");
    }
}

class Green implements Color {

    @Override
    public void fill() {
        System.out.println("green::fill");
    }
}
