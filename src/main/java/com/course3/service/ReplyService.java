package com.course3.service;

import com.course3.beans.Reply;
import com.course3.dao.ReplyMapper;
import com.course3.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.Date;
import java.util.List;

public class ReplyService {

    public static Reply getReplyByRid(Integer rid){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ReplyMapper mapper = sqlSession.getMapper(ReplyMapper.class);
        Reply replyByRid = mapper.getReplyByRid(rid);
        sqlSession.close();
        return replyByRid;
    }

    public static List<Reply> getReplyList(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ReplyMapper mapper = sqlSession.getMapper(ReplyMapper.class);
        List<Reply> replyList = mapper.getReplyList();
        sqlSession.close();
        return replyList;
    }

    public static List<Reply> getReplyListByAid(Integer aid){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ReplyMapper mapper = sqlSession.getMapper(ReplyMapper.class);
        List<Reply> replyListByAid = mapper.getReplyListByAid(aid);
        sqlSession.close();
        return replyListByAid;
    }

    public static boolean addReply(Integer aid, String uname, String details){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ReplyMapper mapper = sqlSession.getMapper(ReplyMapper.class);
        Date date = new Date();
        boolean success = mapper.addReply(new Reply(uname, aid, details, date));
        sqlSession.commit();
        sqlSession.close();
        return success;
    }

    public static boolean deleteReplyByRid(Integer rid){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ReplyMapper mapper = sqlSession.getMapper(ReplyMapper.class);
        boolean success = mapper.deleteReplyByRid(rid);
        sqlSession.commit();
        sqlSession.close();
        return success;
    }
}
