package com.jdbc;


import java.sql.DriverManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.fc.bean.User;

/**
 * @author fangcong on 2017/7/3.
 */
public class JdbcExample {

    private Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://127.0.0.1:3306/test";
            String userName = "root";
            String password = "123456";
            connection = DriverManager.getConnection(url, userName, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public User getUserByLoginName(String loginName) {
        Connection conn = getConnection();
        PreparedStatement ps;
        ResultSet rs;
        try {
            String sql = "SELECT * FROM temp_user WHERE login_name = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, loginName);
            rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setRealName(rs.getString("real_name"));
                user.setAge(rs.getInt("age"));
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        JdbcExample jdbcExample = new JdbcExample();
        User user = jdbcExample.getUserByLoginName("zhangsan");
        if (user != null) {
            System.out.println(user.getRealName() + " : " + user.getAge());
        } else {
            System.out.println("user not exist!");
        }
    }
}
