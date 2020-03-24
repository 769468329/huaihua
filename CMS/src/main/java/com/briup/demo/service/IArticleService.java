package com.briup.demo.service;

import java.util.List;

import com.briup.demo.bean.Article;
import com.briup.demo.utils.CustomerException;

/**
 *文章相关内容的service接口 
 *
 */
public interface IArticleService {
	
	/**
	 * 新增或修改文章
	 */
	public void saveOrUpdateArticle(Article artical) throws CustomerException;
	
	/**
	 * 删除文章
	 */
	public void deleteArticleById(int id) throws CustomerException;
	
	/**
	 * 
	 * @param keyStr 
	 * @param condition
	 * @return
	 * @throws CustomerException
	 */
	public List<Article> findArticleByCondition(String keyStr,String condition) 
			throws CustomerException;
	
	/**
	 * 根据id查询文章
	 */
	public Article findArticleById(int id) throws CustomerException;
	
}
