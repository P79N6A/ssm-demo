package com.fc.aspectj;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

/**
 * <aop:advisor>方式实现切面通知</aop:advisor>
 *
 * @author fangcong on 2018/1/8.
 */
public class CompactDiscAdvisor implements MethodBeforeAdvice, AfterReturningAdvice{

    @Override
    public void afterReturning(Object o, Method method, Object[] objects, Object o1) throws Throwable {
        System.out.println("after returning : applause!");
    }

    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        System.out.println("before : silence cell phones!");
    }
}
