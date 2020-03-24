package com.briup.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.demo.bean.ArticleExample;
import com.briup.demo.bean.Category;
import com.briup.demo.bean.CategoryExample;
import com.briup.demo.bean.ex.CategoryEx;
import com.briup.demo.mapper.ArticleMapper;
import com.briup.demo.mapper.CategoryMapper;
import com.briup.demo.mapper.ex.CategoryExMapper;
import com.briup.demo.service.ICategoryService;
import com.briup.demo.utils.CustomerException;
import com.briup.demo.utils.StatusCodeUtil;
/**
 * 栏目功能
 * 
 * @author Administrator
 *
 */
@Service
public class CategoryServiceImpl implements ICategoryService{
	//调度作用
	//栏目的dao
	@Autowired
	private CategoryMapper categoryMapper;
	//栏目的拓展dao
	@Autowired
	private CategoryExMapper categoryExMapper;
	//文章的dao
	@Autowired
	private ArticleMapper articlemapper;
	
	//查询所有的栏目
	@Override
	public List<Category> findAllCategorys() throws CustomerException {
		
		return categoryMapper.selectByExample(new CategoryExample());
	}
	//添加或修改栏目信息
	@Override
	public void saveOrUpdataCategory(Category category) throws CustomerException {
		if(category==null) {
			throw new CustomerException(StatusCodeUtil.ERROR_CODE, "参数为空");
		}
		if(category.getId()==null) {
			categoryMapper.insert(category);
		}else {
			categoryMapper.updateByPrimaryKey(category);
		}
		
	}
	//根据id删除栏目信息
	@Override
	public void deleteCategoryByid(int id) throws CustomerException {
		//删除栏目的同事，需要先删除栏目中包含的文章的信息。
		ArticleExample example = new ArticleExample();
		example.createCriteria().andCategoryIdEqualTo(id);
		articlemapper.deleteByExample(example);
		categoryMapper.deleteByPrimaryKey(id);
		
	}
	//根据id查找指定的栏目信息
	@Override
	public Category findCategpryById(int id) throws CustomerException {
//		
//		CategoryExample example = new CategoryExample();
//		Criteria criteria = example.createCriteria();
//		criteria.andIdIsNotNull();
		return categoryMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public List<CategoryEx> findAllCategoryEx() throws CustomerException {
		return categoryExMapper.findAllCategoryExs();
	}
	
	
	@Override
	public CategoryEx findCategoryExById(int id) throws CustomerException {
		return categoryExMapper.findCategoryExbyId(id);
	}

}
