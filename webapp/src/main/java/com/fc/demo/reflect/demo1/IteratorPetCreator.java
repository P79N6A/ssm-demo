package com.fc.demo.reflect.demo1;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by fangcong on 2017/4/7.
 */
public class IteratorPetCreator extends AbstractPetCreator {

    public static final List<Class<? extends Pet>> ALL_TYPES = Collections.unmodifiableList(
            Arrays.asList(Pet.class, Cat.class, Dog.class, ExtendsCat.class, ExtendsDog.class)
    );

    public static final List<Class<? extends Pet>> TYPES = ALL_TYPES.subList(
            ALL_TYPES.indexOf(ExtendsCat.class), ALL_TYPES.size()
    );

    @Override
    public List<Class<? extends Pet>> getTypes() {
        return TYPES;
    }
}
