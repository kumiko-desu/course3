package com.course3.dao;

import com.course3.beans.Reply;
import com.course3.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

public class ReplyMapperTest {

    @Test
    public void getReplyListByAid(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        ReplyMapper mapper = sqlSession.getMapper(ReplyMapper.class);
        List<Reply> replyListByAid = mapper.getReplyListByAid(1);
        System.out.println(replyListByAid);

        sqlSession.close();
    }
    @Test
    public void addReply(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        ReplyMapper mapper = sqlSession.getMapper(ReplyMapper.class);
        Date date = new Date();
        boolean success = mapper.addReply(new Reply("123", 1, "reply", date));
        System.out.println(success);

        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void deleteReplyByRid(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        ReplyMapper mapper = sqlSession.getMapper(ReplyMapper.class);
        Date date = new Date();
        boolean success = mapper.deleteReplyByRid(1);
        System.out.println(success);

        sqlSession.commit();
        sqlSession.close();
    }
}
