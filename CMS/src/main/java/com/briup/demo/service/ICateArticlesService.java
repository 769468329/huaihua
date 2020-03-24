package com.briup.demo.service;

import java.util.List;

import com.briup.demo.bean.Article;

public interface ICateArticlesService {
	//查询指定栏目下文章
	List<Article> findCateArticles(String cate);
	//点击查询一篇文章
	Article showOneArticle(int id);
	
}
