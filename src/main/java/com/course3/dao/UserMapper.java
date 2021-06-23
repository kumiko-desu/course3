package com.course3.dao;

import com.course3.beans.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    List<User> getUserList();
    User getUserByUid(Integer uid);
    User getUserByName(String name);
    User getUserByToken(String token);
    boolean addUser(User user);
    boolean changeNameByUid(@Param("uid") Integer uid, @Param("name") String name);
    boolean changePasswordByUid(@Param("uid") Integer uid, @Param("salt") String salt, @Param("hashPwd") String hashPwd);
    boolean changeTokenByUid(@Param("uid") Integer uid, @Param("token") String token);
    boolean deleteUserByUid(Integer uid);
    boolean setAdminByUid(Integer uid);
}
