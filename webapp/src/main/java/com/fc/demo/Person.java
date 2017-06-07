package com.fc.demo;

import com.fc.enums.Gender;

import java.io.Serializable;

/**
 * Created by fangcong on 2017/2/28.
 */
public class Person implements Serializable {

    private static final long serialVersionUID = 1635254709508568532L;

    private String name;
    private Integer age;
    private Gender gender;

    public Person(String name, Integer age, Gender gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                '}';
    }
}
