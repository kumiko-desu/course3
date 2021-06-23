package com.course3.dao;

import com.course3.beans.Favor;
import com.course3.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.util.List;

public class FavorMapperTest {
    @Test
    public void getFavorCountByUid(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        FavorMapper mapper = sqlSession.getMapper(FavorMapper.class);
        Integer count = mapper.getFavorCountByUid(14);
        System.out.println(count);

        sqlSession.close();
    }

    @Test
    public void getFavorCountByAid(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        FavorMapper mapper = sqlSession.getMapper(FavorMapper.class);
        Integer count = mapper.getFavorCountByAid(1);
        System.out.println(count);

        sqlSession.close();
    }

    @Test
    public void getFavorListByUid(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        FavorMapper mapper = sqlSession.getMapper(FavorMapper.class);
        List<Favor> favorListByUid = mapper.getFavorListByUid(14);
        System.out.println(favorListByUid);

        sqlSession.close();
    }

    @Test
    public void getIsFavorByFavor(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        FavorMapper mapper = sqlSession.getMapper(FavorMapper.class);
        boolean success = mapper.getIsFavorByFavor(new Favor(1,1));
        System.out.println(success);

        sqlSession.close();
    }

}
