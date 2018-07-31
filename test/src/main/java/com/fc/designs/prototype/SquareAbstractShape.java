package com.fc.designs.prototype;

/**
 * @author fangcong on 2018/7/31.
 */
public class SquareAbstractShape extends AbstractShape {

    public SquareAbstractShape() {
        type = "square";
    }

    @Override
    void draw() {
        System.out.println("draw square");
    }
}
