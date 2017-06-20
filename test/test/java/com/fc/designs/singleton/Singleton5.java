package com.fc.designs.singleton;

/**
 * Created by fangcong on 2017/2/20.
 */
public enum Singleton5 {

    INSTANCE;

    private Singleton5(){}

    /**
     * 使用枚举类型该获取方法可省略，Singleton5.INSTANCE方式即可
     * @return
     */
    public static Singleton5 getInstance(){
        return INSTANCE;
    }
}
