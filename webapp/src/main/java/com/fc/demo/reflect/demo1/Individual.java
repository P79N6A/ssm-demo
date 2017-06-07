package com.fc.demo.reflect.demo1;

/**
 * Created by fangcong on 2017/4/7.
 */
public class Individual implements Comparable<Individual> {

    private static long counter = 0;
    private static final long id = counter++;
    private String name;

    public Individual() {}

    public Individual(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Individual obj) {
        String first = getClass().getSimpleName();
        String argFirst = obj.getClass().getSimpleName();
        int firstCompare = first.compareTo(argFirst);
        if (firstCompare != 0) {
            return firstCompare;
        }
        if (name != null && obj.name != null) {
            int secondCompare = name.compareTo(obj.name);
            if (secondCompare != 0) {
                return secondCompare;
            }
        }
        return obj.id < id ? -1 : (obj.id == id ? 0 : 1);
    }

    @Override
    public String toString() {
        return "Individual{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Individual && id == ((Individual) o).id;
    }

    @Override
    public int hashCode() {
        int result = 17;
        if (name != null) {
            result = 37 * result + name.hashCode();
        }
        result = 37 * result + (int)id;
        return result;
    }
}
