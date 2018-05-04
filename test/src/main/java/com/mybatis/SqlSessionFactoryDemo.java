package com.mybatis;

import java.io.IOException;
import java.io.InputStream;

import com.fc.bean.Role;
import com.fc.dao.RoleDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * 构建Mapper执行SQL过程
 * SqlSessionFactory——>SqlSession——>Mapper
 *
 * @author fangcong on 2018/5/4.
 */
public class SqlSessionFactoryDemo {

    public static void main(String[] args) {
        String config = "mybatis-config.xml";
        try {
            InputStream is = Resources.getResourceAsStream(config);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
            SqlSession sqlSession = sqlSessionFactory.openSession(true);
            //直接获取Mapper
            RoleDao roleDao = sqlSession.getMapper(RoleDao.class);
            Role role = roleDao.getRole(1L);
            System.out.println(role.toString());

            //namespace+sqlId方式发生SQL获取结果
            Role role1 = sqlSession.selectOne("com.fc.dao.RoleDao.getRole", 1L);
            System.out.println(role1.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
