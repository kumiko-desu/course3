package com.course3.controller;

import com.course3.beans.Reply;
import com.course3.beans.Result;
import com.course3.beans.User;
import com.course3.service.ReplyService;
import com.course3.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reply")
public class ReplyController {
    @RequestMapping("/add")
    public Result addReply(@RequestParam Integer aid,
                           @RequestParam String details,
                           @CookieValue String token){
        User user = UserService.getUserByToken(token);
        ReplyService.addReply(aid, user.getName(), details);
        return new Result(true,0,"success to reply");
    }

    @RequestMapping("/delete")
    public Result addReply(@RequestParam Integer rid,
                           @CookieValue String token){
        User user = UserService.getUserByToken(token);
        Reply reply = ReplyService.getReplyByRid(rid);
        if (reply == null) return new Result(false, -1, "invalid rid");
        if (reply.getUname().equals(user.getName())){
            ReplyService.deleteReplyByRid(rid);
            return new Result(true,0,"(own) success to delete reply");
        }
        if (user.isAdmin()){
            ReplyService.deleteReplyByRid(rid);
            return new Result(true,0,"(admin) success to delete reply");
        }
        return new Result(false,-1,"no right to delete reply");
    }

    @RequestMapping("/list/aid/{aid}")
    public Result getReplyListByAid(@PathVariable Integer aid){
        List<Reply> replyListByAid = ReplyService.getReplyListByAid(aid);
        return new Result(true,0,"success to get replyList", replyListByAid);
    }

    @RequestMapping("/list")
    public Result getReplyList(@CookieValue String token){
        if (UserService.isAdminByToken(token)){
            List<Reply> replyList = ReplyService.getReplyList();
            return new Result(true,0,"success to get replyList", replyList);
        }else{
            return new Result(false,-1,"invalid token or no right");
        }
    }

}
