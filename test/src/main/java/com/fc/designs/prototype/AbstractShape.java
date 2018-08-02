package com.fc.designs.prototype;

/**
 * 原型模式，要求实现Cloneable接口
 *
 * @author fangcong on 2018/7/30.
 */
public abstract class AbstractShape implements Cloneable {

    private Integer id;

    protected String type;

    /**
     * 定义行为区分
     */
    abstract void draw();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    @Override
    public Object clone() {
        Object clone = null;
        try {
            clone = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }

}
