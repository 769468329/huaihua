package com.briup.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.demo.bean.Article;
import com.briup.demo.service.ICateArticlesService;
import com.briup.demo.utils.CustomerException;
import com.briup.demo.utils.Message;
import com.briup.demo.utils.MessageUtil;
import com.briup.demo.utils.StatusCodeUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(description="指定栏目下文章相关接口")
public class CateArticleController {
	
	@Autowired
	private ICateArticlesService cateArticlesService;
	
	@GetMapping("/findArticlesByCate")
	@ApiOperation("根据指定栏目查询文章信息")
	public Message<List<Article>> getArticleByCondition(String cate){
		List<Article> list;
		try {
			list = cateArticlesService.findCateArticles(cate);
			return MessageUtil.success(list);
		} catch (CustomerException e) {
			// TODO Auto-generated catch block
			return MessageUtil.error(StatusCodeUtil.ERROR_CODE, "系统错误");
		}
	}
	
	@GetMapping("/showArticleById")
	@ApiOperation("根据id查询文章信息")
	public Message<Article> getArticleById(int id){
		 Article article = cateArticlesService.showOneArticle(id);
		return MessageUtil.success(article);
	}

}
