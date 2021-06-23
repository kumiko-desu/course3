package com.course3.dao;

import com.course3.beans.User;
import com.course3.utils.MybatisUtils;
import com.course3.utils.UserUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.util.List;

public class UserMapperTest {
    @Test
    public void getUserList(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        try {
            UserMapper userDao = sqlSession.getMapper(UserMapper.class);
            List<User> userList = userDao.getUserList();

            for (User user : userList) {
                System.out.println(user);
            }

        }catch (Exception e){
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void getUserByUid(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User userById = mapper.getUserByUid(10);
        System.out.println(userById);

        sqlSession.close();
    }

    @Test
    public void getUserByName(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User userByName = mapper.getUserByName("12");
        System.out.println(userByName);

        sqlSession.close();
    }

    @Test
    public void addUser(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        boolean success = mapper.addUser(new User("12","salt","1"));
        System.out.println(success);

        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void changeName(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        boolean success = mapper.changeNameByUid(10, "test1");
        System.out.println(success);

        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void changeToken(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        boolean success = mapper.changeTokenByUid(10, "testToken");
        System.out.println(success);

        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void changePassword(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        boolean success = mapper.changePasswordByUid(10, "testSalt", "test");
        System.out.println(success);

        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void deleteUserByName(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        boolean success = mapper.deleteUserByUid(4);
        System.out.println(success);

        sqlSession.commit();
        sqlSession.close();
    }
}
