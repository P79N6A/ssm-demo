package junittest;

import com.fc.bean.TestLombookBean;
import com.fc.service.TestSetBean;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author SYSTEM on 2017/11/9.
 */
public class GetBeanTest {

    /**
     * xml方式配置bean,读取的xml文件必须能扫描所有bean，否则会抛出不能创建bean异常<br>
     * 见web.xml中contextConfigLocation
     */
    @Test
    public void test() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-application.xml");
        TestSetBean testSetBean = applicationContext.getBean(TestSetBean.class);
        System.out.println(testSetBean.getClass().getName());
        Assert.assertNotNull(testSetBean);
    }

    /**
     * 使用@Configuration注解和@Bean声明bean，spring会扫描该注解下所有bean
     */
    @Test
    public void testConfigBean() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfigBean.class);
        TestSetBean testSetBean = applicationContext.getBean(TestSetBean.class);
        Assert.assertNotNull(testSetBean);
        testSetBean.getback();
    }

    @Test
    public void testLomBook() {
        TestLombookBean bean = new TestLombookBean();
        bean.setAge(11);
        bean.name("zhangsan");
    }
}
