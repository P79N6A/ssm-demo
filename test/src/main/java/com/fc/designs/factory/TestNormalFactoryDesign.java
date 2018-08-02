package com.fc.designs.factory;

/**
 * @author fangcong on 2018/4/17.
 */
public class TestNormalFactoryDesign {

    public static void main(String[] args) {
        Fruit f = FruitFactory.getInstance(Apple.class.getName());
        if (null != f) {
            f.eat();
        }
    }
}

interface Fruit {

    /**
     * 定义接口行为方法
     */
    void eat();
}

class Apple implements Fruit {

    @Override
    public void eat() {
        System.out.println("eat apple");
    }
}

class Orange implements Fruit {

    @Override
    public void eat() {
        System.out.println("eat orange");
    }
}

class FruitFactory {

    public static Fruit getInstance(String className) {
        try {
            return (Fruit)Class.forName(className).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
