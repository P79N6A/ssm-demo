package com.jvm.dispatcher;

/**
 * @author fangcong on 2017/5/10.
 * 动态分派:根据实际类型指定
 */
public class DynamicDispatcher {

    static abstract class AbstractHuman {
        protected abstract void sayHello();
    }

    static class Man extends AbstractHuman {

        @Override
        protected void sayHello() {
            System.out.println("man");
        }
    }

    static class Woman extends AbstractHuman {

        @Override
        protected void sayHello() {
            System.out.println("woman");
        }
    }

    public static void main(String[] args) {
        AbstractHuman man = new Man();
        AbstractHuman women = new Woman();

        man.sayHello();
        women.sayHello();

        man = new Woman();
        man.sayHello();
    }
}
