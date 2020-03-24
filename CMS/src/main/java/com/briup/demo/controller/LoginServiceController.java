package com.briup.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.demo.bean.Customer;
import com.briup.demo.service.ILoginService;
import com.briup.demo.utils.CustomerException;
import com.briup.demo.utils.Message;
import com.briup.demo.utils.MessageUtil;
import com.briup.demo.utils.StatusCodeUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(description="用户登录接口")
public class LoginServiceController {
	@Autowired
	private ILoginService loginService;
	
	@GetMapping("/addCustomer")
	@ApiOperation("保存账号")
	public Message<String> getArticleById(Customer customer){
		try {
			loginService.saveOrUpdate(customer);
			return MessageUtil.success();
					
		} catch (CustomerException e) {
			// TODO Auto-generated catch block
			return MessageUtil.error(StatusCodeUtil.ERROR_CODE, "系统错误："+e.getMessage());
		}
	}
	@PostMapping("/updaCustomer")
	@ApiOperation("修改账号信息")
	public Message<String> updateLink(Customer customer){ 
		loginService.saveOrUpdate(customer);
		return MessageUtil.success();
		
	}
	@PostMapping("/login")
	@ApiOperation("修改账号信息")
	public Message<Customer> login(String username,String password){ 
		Customer customer = loginService.login(username, password);
		return MessageUtil.success(customer);
		
	}

}
