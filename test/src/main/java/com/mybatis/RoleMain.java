package com.mybatis;

import com.fc.bean.Role;
import com.fc.dao.RoleDao;
import org.apache.ibatis.session.SqlSession;

/**
 * @author fangcong on 2018/5/4.
 */
public class RoleMain {

    public static void main(String[] args) {
        SqlSession sqlSession = null;
        try {
            sqlSession = SqlSessionFactoryUtil.getSqlSession();
            RoleDao roleDao = sqlSession.getMapper(RoleDao.class);
            Role role = new Role();
            role.setRoleName("部门管理员");
            role.setRoleType("dept");
            roleDao.insertRole(role);
            sqlSession.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            if (null != sqlSession) {
                sqlSession.rollback();
            }
        } finally {
            if (null != sqlSession) {
                sqlSession.close();
            }
        }
    }
}
