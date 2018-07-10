package com.fc.mybatis;

import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;

/**
 * 注解表示是拦截器以及拦截的条件，方法和参数
 *
 * @author fangcong on 2018/7/10.
 */
@Intercepts({
    @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})
})
public class MyExecutorInterceptorPlugin implements Interceptor {

    Properties properties = null;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.err.println("before invocation...");
        Object obj = invocation.proceed();
        System.err.println("after invocation...");
        return obj;
    }

    @Override
    public Object plugin(Object target) {
        System.err.println("调用生成代理对象");
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        System.err.println("打印插件属性值dbType=" + properties.getProperty("dbType"));
        this.properties = properties;
    }
}
