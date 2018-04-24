package com.fc.designs.factory;

/**
 * @author fangcong on 2018/4/11.
 */
public class FactoryProducer {

    public static AbstractFactory getFactory(String choice) {
        if ("shape".equalsIgnoreCase(choice)) {
            return new ShapeFactory();
        }
        if ("color".equalsIgnoreCase(choice)) {
            return new ColorFactory();
        }
        return null;
    }

    public static void main(String[] args) {
        AbstractFactory shapeFactory = getFactory("shape");
        if (null != shapeFactory) {
            Shape shape1 = shapeFactory.getShape("rectangle");
            shape1.draw();

            Shape shape2 = shapeFactory.getShape("square");
            shape2.draw();
        } else {
            System.out.println("can not get shape factory");
        }

        AbstractFactory colorFactory = getFactory("color");
        if (null != colorFactory) {
            Color color1 = colorFactory.getColor("red");
            color1.fill();

            Color color2 = colorFactory.getColor("green");
            color2.fill();
        } else {
            System.out.println("can not get color factory");
        }
    }
}
