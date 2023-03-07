package com.liutao.dao;

import com.liutao.pojo.User;

public interface UserDao {
    User findByUsername(String username);
}
