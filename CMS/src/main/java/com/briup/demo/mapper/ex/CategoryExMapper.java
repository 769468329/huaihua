package com.briup.demo.mapper.ex;
/**
 * 处理 查询栏目及其包含的文章信息
 * @author Administrator
 *
 */

import java.util.List;

import com.briup.demo.bean.ex.CategoryEx;

public interface CategoryExMapper {
	/**
	 * 实现查询所有栏目及其包含的文章信息
	 * @return
	 */
	List<CategoryEx> findAllCategoryExs();
	
	/**
	 * 通过id查询对应栏目及其包含的文章信息
	 */
	CategoryEx findCategoryExbyId(int id);
	
	
}

