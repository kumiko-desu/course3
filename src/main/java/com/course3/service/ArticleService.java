package com.course3.service;

import com.course3.beans.Article;
import com.course3.dao.ArticleMapper;
import com.course3.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class ArticleService {

    public static List<Article> getArticleList(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ArticleMapper mapper = sqlSession.getMapper(ArticleMapper.class);
        List<Article> articles = mapper.getArticleList();
        sqlSession.close();
        return articles;
    }

    public static List<Article> getArticleListByAuthor(String author){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ArticleMapper mapper = sqlSession.getMapper(ArticleMapper.class);
        List<Article> articles = mapper.getArticleListByAuthor(author);
        sqlSession.close();
        return articles;
    }

    public static Article getArticleByAid(Integer aid){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ArticleMapper mapper = sqlSession.getMapper(ArticleMapper.class);
        Article article = mapper.getArticleByAid(aid);
        sqlSession.close();
        return article;
    }

    public static List<Article> getArticleListByTitle(String keyword){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ArticleMapper mapper = sqlSession.getMapper(ArticleMapper.class);
        List<Article> articles = mapper.getArticleListByTitle(keyword);
        sqlSession.close();
        return articles;
    }

    public static boolean addArticle(Article article){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ArticleMapper mapper = sqlSession.getMapper(ArticleMapper.class);
        boolean success = mapper.addArticle(article);
        sqlSession.commit();
        sqlSession.close();
        return success;
    }

    public static boolean deleteArticleByAid(Integer aid){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ArticleMapper mapper = sqlSession.getMapper(ArticleMapper.class);
        boolean success = mapper.deleteArticleByAid(aid);
        sqlSession.commit();
        sqlSession.close();
        return success;
    }

    public static boolean addArticleReadersByAid(Integer aid){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ArticleMapper mapper = sqlSession.getMapper(ArticleMapper.class);
        boolean success = mapper.addArticleReadersByAid(aid);
        sqlSession.commit();
        sqlSession.close();
        return success;
    }
}
