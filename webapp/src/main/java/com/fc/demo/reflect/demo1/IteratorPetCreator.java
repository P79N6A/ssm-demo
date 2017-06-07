package com.fc.demo.reflect.demo1;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by fangcong on 2017/4/7.
 */
public class IteratorPetCreator extends PetCreator {

    public static final List<Class<? extends Pet>> allTypes = Collections.unmodifiableList(
            Arrays.asList(Pet.class, Cat.class, Dog.class, ExtendsCat.class, ExtendsDog.class)
    );

    public static final List<Class<? extends Pet>> types = allTypes.subList(
            allTypes.indexOf(ExtendsCat.class), allTypes.size()
    );

    @Override
    public List<Class<? extends Pet>> getTypes() {
        return types;
    }
}
