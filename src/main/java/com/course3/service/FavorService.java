package com.course3.service;

import com.course3.beans.Favor;
import com.course3.dao.FavorMapper;
import com.course3.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class FavorService {

    public static boolean setFavor(Integer uid, Integer aid, boolean isFavor){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        FavorMapper mapper = sqlSession.getMapper(FavorMapper.class);
        Favor favor = new Favor(uid, aid);
        boolean isFavorNow = mapper.getIsFavorByFavor(favor);
        Boolean success;
        if (isFavorNow == isFavor){
            return true;
        } else {
            if (isFavor) {
                success = mapper.addFavor(favor);
            } else {
                success = mapper.deleteFavor(favor);
            }
            sqlSession.commit();
        }
        sqlSession.close();
        return success;
    }

    public static List<Favor> getFavorListByUid(Integer uid){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        FavorMapper mapper = sqlSession.getMapper(FavorMapper.class);
        List<Favor> favors = mapper.getFavorListByUid(uid);
        sqlSession.close();
        return favors;
    }

    public static Integer getFavorCountByAid(Integer aid){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        FavorMapper mapper = sqlSession.getMapper(FavorMapper.class);
        Integer favorCount = mapper.getFavorCountByAid(aid);
        sqlSession.close();
        return favorCount;
    }

    public static Integer getFavorCountByUid(Integer uid){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        FavorMapper mapper = sqlSession.getMapper(FavorMapper.class);
        Integer favorCount = mapper.getFavorCountByUid(uid);
        sqlSession.close();
        return favorCount;
    }

    public static boolean getIsFavor(Integer uid, Integer aid){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        FavorMapper mapper = sqlSession.getMapper(FavorMapper.class);
        boolean success = mapper.getIsFavorByFavor(new Favor(uid,aid));
        sqlSession.close();
        return success;
    }
}
