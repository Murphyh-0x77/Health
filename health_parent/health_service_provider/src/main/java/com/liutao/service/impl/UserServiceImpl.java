package com.liutao.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.liutao.dao.PermissionDao;
import com.liutao.dao.RoleDao;
import com.liutao.dao.UserDao;
import com.liutao.pojo.Permission;
import com.liutao.pojo.Role;
import com.liutao.pojo.User;
import com.liutao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * 用户服务
 */
@Service(interfaceClass = UserService.class)
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PermissionDao permissionDao;

    @Override
    public User findByUsername(String username) {
        User user = userDao.findByUsername(username);//查询用户基本信息，不包含用户的角色
        if(user == null){
            return null;
        }
        Integer id = user.getId();
        //根据用户id查询对应的角色
        Set<Role> roles = roleDao.findByUserId(id);
        for (Role role : roles) {
            Integer roleId = role.getId();
            //根据角色id查询关联的权限
            Set<Permission> permissions = permissionDao.findByRoleId(roleId);
            role.setPermissions(permissions);//让角色关联权限
        }
        user.setRoles(roles);//让用户关联角色
        return user;
    }
}
