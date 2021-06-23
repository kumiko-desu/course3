package com.course3.dao;

import com.course3.beans.Reply;

import java.util.List;

public interface ReplyMapper {
    Reply getReplyByRid(Integer rid);
    List<Reply> getReplyList();
    List<Reply> getReplyListByAid(Integer aid);
    boolean addReply(Reply reply);
    boolean deleteReplyByRid(Integer rid);
}
