package com.fc.demo.reflect.demo1;

import java.util.List;

/**
 * Created by fangcong on 2017/4/7.
 */
public class Test {

    public static void main(String[] args) {
        IteratorPetCreator test = new IteratorPetCreator();
        List<Class<? extends Pet>> list = test.getTypes();
        for (Class<?> clazz : list) {
            System.out.println(clazz.toString());
        }

        TypeCounter counter = new TypeCounter(Pet.class);
        counter.count(new Cat());
        counter.count(new Dog());
        System.out.println(counter.toString());

        loop : for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 5; j++) {
                break loop;
            }
        }

    }
}
