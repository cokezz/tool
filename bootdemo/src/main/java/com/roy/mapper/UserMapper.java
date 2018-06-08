package com.roy.mapper;

import com.roy.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

@Mapper
public interface UserMapper {

    User findUserByUserid(String userId);

    boolean insertUser (User user);

    User findUserByUserName(String userName);
}
