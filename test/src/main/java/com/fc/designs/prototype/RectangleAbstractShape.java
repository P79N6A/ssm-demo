package com.fc.designs.prototype;

/**
 * @author fangcong on 2018/7/31.
 */
public class RectangleAbstractShape extends AbstractShape {

    public RectangleAbstractShape() {
        type = "rectangle";
    }

    @Override
    void draw() {
        System.out.println("draw rectangle");
    }
}
