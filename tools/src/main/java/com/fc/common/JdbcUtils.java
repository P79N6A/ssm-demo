package com.fc.common;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * @author fangcong on 2018/11/21.
 */
public class JdbcUtils {

    private static final Properties MYSQL_PROPERTIES;

    static {
        MYSQL_PROPERTIES = new Properties();
        InputStream is = JdbcUtils.class.getResourceAsStream("/jdbc.properties");
        try {
            MYSQL_PROPERTIES.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        java.sql.Connection connection = null;
        try {
            Class.forName(MYSQL_PROPERTIES.getProperty("driver", "com.mysql.jdbc.Driver"));
            String url = MYSQL_PROPERTIES.getProperty("url", "jdbc:mysql://127.0.0.1:3306/test");
            String userName = MYSQL_PROPERTIES.getProperty("username", "root");
            String password = MYSQL_PROPERTIES.getProperty("password", "123456");
            connection = DriverManager.getConnection(url, userName, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
