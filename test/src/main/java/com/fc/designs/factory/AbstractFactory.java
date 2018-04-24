package com.fc.designs.factory;

import org.apache.commons.lang.StringUtils;

/**
 * 抽象工厂来获取工厂
 *
 * @author fangcong on 2018/4/11.
 */
public abstract class AbstractFactory {

    abstract Shape getShape(String shape);

    abstract Color getColor(String color);
}

class ShapeFactory extends AbstractFactory {

    @Override
    Shape getShape(String shape) {
        if (StringUtils.isBlank(shape)) {
            return null;
        }
        if ("rectangle".equalsIgnoreCase(shape)) {
            return new Rectangle();
        }
        if ("square".equalsIgnoreCase(shape)) {
            return new Square();
        }
        return null;
    }

    @Override
    Color getColor(String color) {
        return null;
    }
}

class ColorFactory extends AbstractFactory {

    @Override
    Shape getShape(String shape) {
        return null;
    }

    @Override
    Color getColor(String color) {
        if (StringUtils.isBlank(color)) {
            return null;
        }
        if ("red".equalsIgnoreCase(color)) {
            return new Red();
        }
        if ("green".equalsIgnoreCase(color)) {
            return new Green();
        }
        return null;
    }
}
