package com.nowcoder.community.dao;

import com.nowcoder.community.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    //对一些常用的函数的声明，具体实现在mapper中
    User selectById(int id);
    User selectByName(String username);
    User selectByEmail(String email);

    int insertUser(User user);
    int updateStatus(int id, int status);
    int updateHeader(int id, String headerUrl);
    int updatePassword(int id, String password);

}
