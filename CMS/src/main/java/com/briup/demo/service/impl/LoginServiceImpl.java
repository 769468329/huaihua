package com.briup.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.demo.bean.Customer;
import com.briup.demo.bean.CustomerExample;
import com.briup.demo.mapper.CustomerMapper;
import com.briup.demo.service.ILoginService;
import com.briup.demo.utils.CustomerException;
import com.briup.demo.utils.StatusCodeUtil;
@Service
public class LoginServiceImpl implements ILoginService{
	
	@Autowired
	private CustomerMapper customermapper;
	
	@Override
	public void saveOrUpdate(Customer customer) throws CustomerException {
		// TODO Auto-generated method stub
		if(customer==null) {
			throw new CustomerException(StatusCodeUtil.ERROR_CODE, "参数为空");
		}
		if(customer.getId()==null) {
			customermapper.insert(customer);
		}else {
			customermapper.updateByPrimaryKey(customer);
		}
	}

	@Override
	public Customer login(String username, String password) throws CustomerException {
		// TODO Auto-generated method stub
		CustomerExample example = new CustomerExample();
		example.createCriteria().andUsernameEqualTo(username).andPasswordEqualTo(password);
		List<Customer> list = customermapper.selectByExample(example);
		Customer customer = list.get(0);
		return customer;
	}

}
