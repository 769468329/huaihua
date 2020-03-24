package com.briup.demo.controller;
/**
 * 栏目相关的Controller
 */
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.demo.bean.Category;
import com.briup.demo.bean.ex.CategoryEx;
import com.briup.demo.service.ICategoryService;
import com.briup.demo.utils.CustomerException;
import com.briup.demo.utils.Message;
import com.briup.demo.utils.MessageUtil;
import com.briup.demo.utils.StatusCodeUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(description="栏目相关接口")
public class CategoryController {
	
	@Autowired
	private ICategoryService cateService;
	//添加栏目信息
	@PostMapping("/saveCategory")
	@ApiOperation("新增栏目")
	public Message<String> addCategory(Category category){ 
		
		try {
			cateService.saveOrUpdataCategory(category);
			return MessageUtil.success();
					
		} catch (CustomerException e) {
			// TODO Auto-generated catch block
			return MessageUtil.error(StatusCodeUtil.ERROR_CODE, "系统错误："+e.getMessage());
		}
	}
	
	//修改栏目信息
	@PostMapping("/updateCategory")
	@ApiOperation("更改栏目")
	public Message<String> updateCategory(Category category){ 
		cateService.saveOrUpdataCategory(category);
		return MessageUtil.success();
	}
	//查询所有的栏目
	@GetMapping("/findCategorys")
	@ApiOperation("查询所有栏目信息")
	public Message<List<Category>> selectCategorys(){ 
		List<Category> list = cateService.findAllCategorys();
		return MessageUtil.success(list);
	}
	//根据id删除栏目信息
	@GetMapping("/deleteActegoryById")
	@ApiOperation("根据id删除栏目")
	public Message<String> deleteById(int id){ 
		cateService.deleteCategoryByid(id);
		return MessageUtil.success();
	}
	//根据id查找指定的栏目信息
	@PostMapping("/selectCategorysByid")
	@ApiOperation("根据id查找指定的栏目信息")
	public Message<Category> selectCategoryById(int id){ 
		Category category = cateService.findCategpryById(id);
		return MessageUtil.success(category);
	}
	
	/**
	 * 根据Id查找栏目及其包含的所有文章信息
	 */
	@GetMapping("/findCategoryExById")
	@ApiOperation("根据栏目id查找指定的栏目及其文章信息")
	public Message<CategoryEx> findCategoryExById(int id){
		
		return MessageUtil.success(cateService.findCategoryExById(id));
	}

}
