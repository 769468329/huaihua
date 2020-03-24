package com.briup.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.demo.bean.Link;
import com.briup.demo.bean.LinkExample;
import com.briup.demo.bean.LinkExample.Criteria;
import com.briup.demo.mapper.LinkMapper;
import com.briup.demo.service.ILinkService;
import com.briup.demo.utils.CustomerException;
import com.briup.demo.utils.StatusCodeUtil;
/**
 * 操作链接的service的功能类
 * @author Administrator
 *
 */
//管理事务  添加到容器
@Service
public class LinkServiceImpl implements ILinkService {
	@Autowired
	private LinkMapper linkMapper;
	//保存或者修改链接信息
	@Override
	public void saveOrUpdateLink(Link link) throws CustomerException {
		//参数为引用类型，要做判空处理。
		if(link==null) {
			throw new CustomerException(StatusCodeUtil.ERROR_CODE, "参数为空");
		}
		//判断link对象的id是否为空，如果为空则新增链接，如果不为空则修改链接。
		if(link.getId()==null) {
			linkMapper.insert(link);
		}else {
			linkMapper.updateByPrimaryKey(link);
		}
		
	}

	//查询所有链接信息
	@Override
	public List<Link> findAllLinks() throws CustomerException {
//		LinkExample example = new LinkExample();
//		List<Link> list = linkMapper.selectByExample(example);
//		return list;
		return linkMapper.selectByExample(new LinkExample());
	}
	
	
	//根据id删除链表信息
	@Override
	public void deleteLinkById(int id) throws CustomerException {
		// TODO Auto-generated method stub
		linkMapper.deleteByPrimaryKey(id);
		
	}
	//根据链接名 查询链接信息
	@Override
	public List<Link> findLinksByName(String name) throws CustomerException {
		name = name==null ?"":name.trim();
		LinkExample example = new LinkExample();
		//"".equals(name)防止产生空指针异常：name为null时
		if("".equals(name)) {
			//如果搜索条件没写，则返回所有数据
			return linkMapper.selectByExample(example);
		}else {
			//如果搜索条件不为null，则返回满足条件的数据
			Criteria criteria = example.createCriteria();
			criteria.andNameLike("%"+name+"%");
			return linkMapper.selectByExample(example);
			
		}
		
	}




	
	
}
