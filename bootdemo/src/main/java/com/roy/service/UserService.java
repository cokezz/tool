package com.roy.service;

import com.roy.model.User;

public interface UserService {

    boolean regUser(String userName,String pwd);

    User findById(String userId);

    User findByUserName(String userName);
}
