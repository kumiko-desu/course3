package com.course3.controller;

import com.course3.beans.Article;
import com.course3.beans.Favor;
import com.course3.beans.Result;
import com.course3.beans.User;
import com.course3.service.ArticleService;
import com.course3.service.FavorService;
import com.course3.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController()
@RequestMapping("/article")
public class ArticleController {

    @RequestMapping("")
    public Result getArticle(Integer page){
        List<Article> articles =  ArticleService.getArticleList();
        if (articles.isEmpty()) return new Result(false, -1, "no articles");
        return new Result(true,0,"success to get articles", articles);
    }

    @RequestMapping("/favorList")
    public Result getArticleByFavor(@CookieValue String token){
        User user = UserService.getUserByToken(token);
        List<Favor> favorList = FavorService.getFavorListByUid(user.getUid());
        List<Article> articles = new ArrayList<>();
        for (Favor favor : favorList) {
            articles.add(ArticleService.getArticleByAid(favor.getAid()));
        }
        if (articles.isEmpty()) return new Result(true, 0,"no articles", articles);
        return new Result(true,0,"success to get articles", articles);
    }

    @RequestMapping("/author/{name}")
    public Result getArticleByAuthor(@PathVariable("name") String name, Integer page){
        List<Article> articles =  ArticleService.getArticleListByAuthor(name);
        if (articles.isEmpty()) return new Result(false, -1,"no articles", articles);
        return new Result(true,0,"success to get articles", articles);
    }

    @RequestMapping("/aid/{aid}")
    public Result getArticleByAid(@PathVariable("aid") Integer aid){
        Article article =  ArticleService.getArticleByAid(aid);
        if (article == null) return new Result(false, -1, "invalid aid");
        return new Result(true,0,"success to get article", article);
    }

    @RequestMapping("/search")
    public Result getArticleByTitle(@RequestParam String title, Integer page){
        List<Article> articles =  ArticleService.getArticleListByTitle(title);
        if (articles.isEmpty()) return new Result(false, -1,"no articles");
        return new Result(true,0,"success to get articles", articles);
    }

    @RequestMapping("/add")
    public Result addArticle(@RequestParam String title,
                             @RequestParam String content,
                             @CookieValue("token") String token){
        User user = UserService.getUserByToken(token);
        Date date = new Date();
        Article article = new Article(user.getName(), title, content, date);
        ArticleService.addArticle(article);
        return new Result(true,0,"success to add article");
    }

    @RequestMapping("/delete")
    public Result deleteArticle(@RequestParam Integer aid,
                                @CookieValue String token){
        Article article = ArticleService.getArticleByAid(aid);
        User user = UserService.getUserByToken(token);
        if (article == null) return new Result(false, -1, "invalid aid");
        if (article.getAuthor().equals(user.getName())){
            ArticleService.deleteArticleByAid(aid);
            return new Result(true,0,"(own) success to delete article");
        }
        if (user.isAdmin()){
            ArticleService.deleteArticleByAid(aid);
            return new Result(true,0,"(admin) success to delete article");
        }
        return new Result(false,-1,"no right to delete article");
    }

    @RequestMapping("/addReader")
    public Result addReader(@RequestParam Integer aid){
        ArticleService.addArticleReadersByAid(aid);
        return new Result(true,0,"success to add readers");
    }

}
