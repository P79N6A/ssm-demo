package com.fc.designs.prototype;

/**
 * @author fangcong on 2018/7/31.
 */
public class CircleAbstractShape extends AbstractShape {

    public CircleAbstractShape() {
        type = "circle";
    }

    @Override
    void draw() {
        System.out.println("draw circle");
    }
}
