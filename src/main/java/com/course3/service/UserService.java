package com.course3.service;

import com.course3.beans.User;
import com.course3.dao.UserMapper;
import com.course3.utils.MybatisUtils;
import com.course3.utils.UserUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.UUID;

public class UserService {

    public static List<User> getUserList(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = mapper.getUserList();
        sqlSession.close();
        return userList;
    }

    public static User getUserByName(String name){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User userByName = mapper.getUserByName(name);
        sqlSession.close();
        return userByName;
    }

    public static User getUserByUid(Integer uid){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User userByUid = mapper.getUserByUid(uid);
        sqlSession.close();
        return userByUid;
    }

    public static User getUserByToken(String token){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User userByToken = mapper.getUserByToken(token);
        sqlSession.close();
        return userByToken;
    }

    public static boolean existUserByName(String name){
        User user = getUserByName(name);
        if (user != null) return true;
        return false;
    }

    public static boolean existUserByUid(Integer uid){
        User user = getUserByUid(uid);
        if (user != null) return true;
        return false;
    }

    public static boolean existUserByToken(String token){
        User user = getUserByToken(token);
        if (user != null) return true;
        return false;
    }

    public static String generateToken(Integer uid){
        String token = UUID.randomUUID().toString();
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.changeTokenByUid(uid, token);
        sqlSession.commit();
        sqlSession.close();
        return token;
    }

    public static boolean addUser(User user){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        user.setToken(UUID.randomUUID().toString());
        boolean success = mapper.addUser(user);
        sqlSession.commit();
        sqlSession.close();
        return success;
    }

    public static boolean isAdminByToken(String token){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.getUserByToken(token);
        if (user == null){
            return false;
        }
        sqlSession.close();
        return user.isAdmin();
    }

    public static boolean changePassword(Integer uid, String password){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        String salt = UserUtils.createSalt();
        String hashPwd = UserUtils.createHashPwd(password, salt);
        boolean success = mapper.changePasswordByUid(uid, salt, hashPwd);
        sqlSession.commit();
        sqlSession.close();
        return success;
    }

    public static boolean deleteUserByUid(Integer uid){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        boolean success = mapper.deleteUserByUid(uid);
        sqlSession.commit();
        sqlSession.close();
        return success;
    }

    public static boolean setAdminByUid(Integer uid){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        boolean success = mapper.setAdminByUid(uid);
        sqlSession.commit();
        sqlSession.close();
        return success;
    }
}
