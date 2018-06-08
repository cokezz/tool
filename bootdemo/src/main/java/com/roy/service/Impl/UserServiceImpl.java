package com.roy.service.Impl;

import com.roy.model.User;
import com.roy.mapper.UserMapper;
import com.roy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    @Transactional
    public boolean regUser(String userName, String pwd) {
        User user = new User();
        user.setPassword(pwd);
        user.setUserName(userName);
        user.setCreateDate(new Date());
        userMapper.insertUser(user);
        return true;
    }

    @Override
    public User findById(String userId) {
        return userMapper.findUserByUserid(userId);
    }

    @Override
    public User findByUserName(String userName) {
        return userMapper.findUserByUserName(userName);
    }
}
