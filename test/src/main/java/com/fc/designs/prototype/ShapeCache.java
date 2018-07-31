package com.fc.designs.prototype;

import java.util.Hashtable;

/**
 * @author fangcong on 2018/7/31.
 */
public class ShapeCache {

    private static Hashtable<Integer, AbstractShape> shapeHashtable = new Hashtable<>();

    public static AbstractShape getShape(Integer id) {
        AbstractShape abstractShape = shapeHashtable.get(id);
        return (AbstractShape)abstractShape.clone();
    }

    public static void loadShape() {
        RectangleAbstractShape rectangleShape = new RectangleAbstractShape();
        rectangleShape.setId(1);
        shapeHashtable.put(rectangleShape.getId(), rectangleShape);

        SquareAbstractShape squareShape = new SquareAbstractShape();
        squareShape.setId(2);
        shapeHashtable.put(squareShape.getId(), squareShape);

        CircleAbstractShape circleShape = new CircleAbstractShape();
        circleShape.setId(3);
        shapeHashtable.put(circleShape.getId(), circleShape);
    }
}
