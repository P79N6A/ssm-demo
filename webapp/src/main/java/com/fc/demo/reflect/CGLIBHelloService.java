package com.fc.demo.reflect;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

/**
 * Created by fangcong on 2017/4/18.
 * @author fangcong
 */
public class CGLIBHelloService implements MethodInterceptor {

    private Object target;

    /**
     * 创建代理对象
     * @param target
     * @return
     */
    public Object getInstance(Object target) {
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("********CGLIB代理*********");
        System.out.println("反射方法调用前...");
        Object returnObj = methodProxy.invokeSuper(o, objects);
        System.out.println("反射方法调用后...");
        return returnObj;
    }
}
