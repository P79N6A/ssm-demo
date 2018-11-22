package test.test;

/**
 * @author fangcong on 2018/10/30.
 */
public class TestStaticClass {

    /**
     * 静态代码块，可以给之后定义的静态变量赋值，但不能访问(会提示非法的向前引用)
     */
    static {
        i = 0;
    }

    private static int i;

    /**
     * 普通内部类
     * 1、不允许public修饰，不允许定义静态变量
     * 2、不允许定义静态代码块,静态方法
     * 3、创建时要通过外部类的实例引用,如m.new N();
     */
    class NormalClass {

        private String a;

        public void a(){}
    }

    /**
     * 静态内部类
     * 1、可定义为public，使用可不依赖于外部类
     * 2、可定义静态和非静态成员变量,方法等
     * 3、创建时可直接创建或外部类引用方式如new A.B();
     */
    public static class StaticClass {

        private String a;

        private static String b;

        private static final String C = "c";
    }

    public static void main(String[] args) {
        TestStaticClass m = new TestStaticClass();
        NormalClass normalClass = m.new NormalClass();

        StaticClass staticClass = new StaticClass();

        StaticClass staticClass1 = new TestStaticClass.StaticClass();
    }
}
