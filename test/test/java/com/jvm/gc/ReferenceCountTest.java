package com.jvm.gc;

/**
 * @author fangcong on 2017/5/10.
 * jvm 引用计数测试
 */
public class ReferenceCountTest {

    /*************************************************
     * 引用计数算法：给对象中添加一个引用计数器，每当有一个
     * 地方引用时，计数器加1；引用失效时，计数器减1；任何
     * 时刻计数器为0的对象就是不可能再被使用的。
     * 可达性算法：GC Roots接口作为起始节点向下搜索，如果没
     * 有一条引用链与对象相连，则证明此对象是不可用的，可回收
     ************************************************/

    /**
     * 定义引用变量
     */
    public Object instance = null;

    private static final int SIZE_1MB = 1024 * 1024;

    /**
     * 该变量存在的唯一意义是占点内存，在GC日志看是否被回收过
     */
    private byte[] bigSize = new byte[2 * SIZE_1MB];

    public static void main(String[] args) {
        ReferenceCountTest objA = new ReferenceCountTest();
        ReferenceCountTest objB = new ReferenceCountTest();

        objA.instance = objB;
        objB.instance = objA;

        objA = null;
        objB = null;

        //objA与objB能否回收？对象回收
        System.gc();
    }
}
