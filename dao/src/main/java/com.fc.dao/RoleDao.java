package com.fc.dao;

import com.fc.bean.Role;

/**
 * @author fangcong on 2018/5/4.
 */
public interface RoleDao {

    /**
     * 根据id查询角色
     *
     * @param id 角色id
     * @return
     */
    Role getRole(Long id);

    /**
     * 添加角色
     *
     * @param role
     * @return
     */
    Integer insertRole(Role role);
}
