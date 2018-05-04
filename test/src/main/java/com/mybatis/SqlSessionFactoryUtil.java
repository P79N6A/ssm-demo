package com.mybatis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * @author fangcong on 2018/5/4.
 */
public class SqlSessionFactoryUtil {

    private static SqlSessionFactory sqlSessionFactory = null;

    private static final Class OBJECT_LOCK = SqlSessionFactoryUtil.class;

    private SqlSessionFactoryUtil() {}

    public static SqlSessionFactory initSqlSessionFactory() {
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        } catch (IOException e) {
            Logger.getLogger(SqlSessionFactoryUtil.class.getName()).log(Level.ERROR
                , null, e);
        }
        synchronized (OBJECT_LOCK) {
            if (sqlSessionFactory == null) {
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            }
        }
        return sqlSessionFactory;
    }

    public static SqlSession getSqlSession() {
        if (null == sqlSessionFactory) {
            initSqlSessionFactory();
        }
        return sqlSessionFactory.openSession();
    }
}
