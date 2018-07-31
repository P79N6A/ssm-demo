package com.fc.designs.prototype;

/**
 * @author fangcong on 2018/7/31.
 */
public class TestPrototype {

    public static void main(String[] args) {
        ShapeCache.loadShape();

        AbstractShape abstractShape1 = ShapeCache.getShape(1);
        System.out.println("shape:" + abstractShape1.getType());

        AbstractShape abstractShape2 = ShapeCache.getShape(2);
        System.out.println("shape:" + abstractShape2.getType());

        AbstractShape abstractShape3 = ShapeCache.getShape(3);
        System.out.println("shape:" + abstractShape3.getType());
    }
}
