package com.course3.controller;

import com.course3.beans.Article;
import com.course3.beans.Favor;
import com.course3.beans.Result;
import com.course3.beans.User;
import com.course3.service.ArticleService;
import com.course3.service.FavorService;
import com.course3.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favor")
public class FavorController {
    @RequestMapping("/is")
    public Result getIsFavor(@RequestParam Integer aid,
                              @CookieValue String token){
        User user = UserService.getUserByToken(token);
        boolean isFavor = FavorService.getIsFavor(user.getUid(), aid);
        return new Result(true,0,"success to get isFavor", isFavor);
    }

    @RequestMapping("/setFavor")
    public Result addFavor(@RequestParam Integer aid,
                           @RequestParam boolean isFavor,
                           @CookieValue String token){
        User user = UserService.getUserByToken(token);
        Article article = ArticleService.getArticleByAid(aid);
        if (article != null){
            FavorService.setFavor(user.getUid(), aid, isFavor);
            return new Result(true,0,"success to set favor");
        } else {
            return new Result(false,-1,"invalid aid");
        }
    }

    @RequestMapping("/list")
    public Result getFavorList(@CookieValue String token){
        User user = UserService.getUserByToken(token);
        List<Favor> favorListByUid = FavorService.getFavorListByUid(user.getUid());
        return new Result(true,0,"success to get favor", favorListByUid);
    }

    @RequestMapping("/count")
    public Result getFavorCountByToken(@CookieValue String token){
        User user = UserService.getUserByToken(token);
        Integer count = FavorService.getFavorCountByUid(user.getUid());
        return new Result(true,0,"success to get favorCount", count);
    }

//    @RequestMapping("/count")
//    public Result getFavorCountByToken(@CookieValue String token){
//        User user = UserService.getUserByToken(token);
//        Integer count = FavorService.getFavorCountByUid(user.getUid());
//        return new Result(true,0,"success to get favor", count);
//    }
}
