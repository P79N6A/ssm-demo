package com.fc.mybatis;

import java.util.List;
import java.util.Properties;

import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.log4j.Logger;

/**
 * @author fangcong on 2018/7/3.
 */
public class MyObjectFactory extends DefaultObjectFactory {

    private static final long serialVersionUID = 1408721723533045607L;

    private static final Logger log = Logger.getLogger(MyObjectFactory.class);

    @Override
    public void setProperties(Properties properties) {
        log.info("定制属性：" + properties);
        super.setProperties(properties);
    }

    @Override
    public <T> T create(Class<T> type) {
        log.info("使用对象工厂的create方法构建单个对象");
        return super.create(type);
    }

    @Override
    public <T> T create(Class<T> type, List<Class<?>> constructorArgTypes, List<Object> constructorArgs) {
        log.info("构建对象列表");
        return super.create(type, constructorArgTypes, constructorArgs);
    }

}
