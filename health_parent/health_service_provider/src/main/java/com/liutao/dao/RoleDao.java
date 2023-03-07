package com.liutao.dao;

import com.liutao.pojo.Role;

import java.util.Set;

public interface RoleDao {
    Set<Role> findByUserId(Integer id);
}
