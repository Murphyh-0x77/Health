package com.liutao.service;

import com.liutao.pojo.User;

public interface UserService {
    User findByUsername(String username);
}
