package com.demo;

import com.fc.bean.TestBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author SYSTEM on 2017/10/23.
 */
public class SpringContextMain {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext xmlApplicationContext = new ClassPathXmlApplicationContext(
            "classpath*:spring-bz.xml");
        TestBean testBean = xmlApplicationContext.getBean(TestBean.class);
        System.out.println(testBean.getClass().getName());
    }
}
