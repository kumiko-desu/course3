package com.course3.dao;

import com.course3.beans.Article;

import java.util.List;

public interface ArticleMapper {
    Article getArticleByAid(Integer aid);
    List<Article> getArticleList();
    List<Article> getArticleListByAuthor(String author);
    List<Article> getArticleListByTitle(String title);
    boolean addArticle(Article article);
    boolean deleteArticleByAid(Integer aid);
    boolean addArticleReadersByAid(Integer aid);
}
