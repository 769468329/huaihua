package com.briup.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.demo.bean.Article;
import com.briup.demo.bean.ArticleExample;
import com.briup.demo.bean.Category;
import com.briup.demo.bean.CategoryExample;
import com.briup.demo.mapper.ArticleMapper;
import com.briup.demo.mapper.CategoryMapper;
import com.briup.demo.service.ICateArticlesService;

/**
 * 查询特定栏目下的文章
 * @author Administrator
 *
 */
@Service
public class CateArticlesServiceImpl implements ICateArticlesService{
	
	
	@Autowired
	private ArticleMapper articleMapper;
	@Autowired
	private CategoryMapper categoryMapper;
	@Override
	public List<Article> findCateArticles(String cate) {
		ArticleExample example = new ArticleExample();
		if("".equals(cate)) {
			return articleMapper.selectByExample(example);
		}else {
			CategoryExample categoryExample = new CategoryExample();
			categoryExample.createCriteria().andNameEqualTo(cate);
			List<Category> category = categoryMapper.selectByExample(categoryExample);
			if(category.size()>0) {
				example.createCriteria().andCategoryIdEqualTo(category.get(0).getId());
			}
			return articleMapper.selectByExample(example);
		}
	}
	@Override
	public Article showOneArticle(int id) {
		
//		Article article = articleMapper.selectByPrimaryKey(id);
//		Integer clickTime=null;
//		if(article.getClicktimes()!=null) {
//			clickTime=article.getClicktimes();
//		}
//		article.setClicktimes(clickTime+1);
//		ArticleServiceImpl impl = new ArticleServiceImpl();
//		impl.saveOrUpdateArticle(article);
		Article article = articleMapper.selectByPrimaryKey(id);
		//添加点击次数
		Integer clickTime=article.getClicktimes()==null?0:article.getClicktimes();
		
		article.setClicktimes(clickTime+1);
		ArticleServiceImpl impl = new ArticleServiceImpl();
		impl.saveOrUpdateArticle(article);
		
		return article;
	}

}
