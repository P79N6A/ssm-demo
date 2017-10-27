package com.fc.aspectj;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * 定义切面，关注CompactDisc接口方法执行
 * 通过@PointCut可以声明频繁使用的切点表达式
 * @author SYSTEM on 2017/10/25.
 */
//@Aspect
public class CompactDiscAspect {

    /*@Pointcut(value = "execution(* com.fc.service.CompactDisc.play(..))")
    public void play() {}

    *//**
     * @Around 注解
     * 使用环绕通知重新实现切面，可替代前置后置和返回通知
     * @param proceedingJoinPoint 调用被通知的方法
     *//*
    @Around("play()")
    public void watchPaly(ProceedingJoinPoint proceedingJoinPoint) {
        try {
            System.out.println("before : silence cell phones!");
            System.out.println("after : go back!");
            proceedingJoinPoint.proceed();
            System.out.println("after returning : applause!");
        } catch (Throwable t) {
            System.out.println("after failure : just a kidding!");
        }
    }*/

    public void silenceCellPhones() {
        System.out.println("before : silence cell phones!");
    }

    public void goBack() {
        System.out.println("after : go back!");
    }

    public void applause() {
        System.out.println("after returning : applause!");
    }

    public void demandRefund() {
        System.out.println("after failure : just a kidding!");
    }
}
