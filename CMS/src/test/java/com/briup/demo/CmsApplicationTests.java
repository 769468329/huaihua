package com.briup.demo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.briup.demo.bean.Category;
import com.briup.demo.service.ICategoryService;

@SpringBootTest
class CmsApplicationTests {
	@Autowired
	private ICategoryService categoryService;
	//实现什么功能
	@Test
	void CategoryController() {
		List<Category> list = categoryService.findAllCategorys();
		list.stream().forEach(System.out::println);
	}

}
