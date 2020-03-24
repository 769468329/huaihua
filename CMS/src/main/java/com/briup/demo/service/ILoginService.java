package com.briup.demo.service;

import com.briup.demo.bean.Customer;
import com.briup.demo.utils.CustomerException;

public interface ILoginService {
	//保存或修改用户名
	void saveOrUpdate(Customer customer) throws CustomerException;
	//登录是否成功
	Customer login(String username,String password) throws CustomerException;
	
}
