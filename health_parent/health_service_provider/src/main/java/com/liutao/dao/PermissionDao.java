package com.liutao.dao;

import com.liutao.pojo.Permission;

import java.util.Set;

public interface PermissionDao {
    Set<Permission> findByRoleId(Integer id);
}
