package com.cx.oa.yjsy.service.hr.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import liquibase.util.MD5Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cx.oa.yjsy.base.Page;
import com.cx.oa.yjsy.dao.hr.UserDao;
import com.cx.oa.yjsy.enitty.hr.RoleInfo;
import com.cx.oa.yjsy.enitty.hr.UserInfo;
import com.cx.oa.yjsy.service.hr.UserService;
import com.cx.oa.yjsy.util.QueryFilter;


 
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	public Serializable save(UserInfo user) {
		user.setPassword(MD5Util.computeMD5("123"));
		return userDao.save(user);
	}

	public UserInfo get(Class<UserInfo> c, Serializable id) {
		return userDao.get(c,id);
	}

	public UserInfo load(Class<UserInfo> c, Serializable id) {
		return userDao.load(c,id);
	}

	public UserInfo get(String hql, Map<String, Object> params) {
		return userDao.get(hql, params);
	}

	public UserInfo getObject(String hql, Object[] params) {
		 
		return userDao.getObject(hql, params);
	}

	public UserInfo checkLogin(UserInfo user) {
		user.setPassword(MD5Util.computeMD5(user.getPassword()));
		return userDao.getObject("from UserInfo where no=? and password=?",new  String[]{user.getNo(),user.getPassword()} );
	}

	public List<UserInfo> queryAll(Class<UserInfo> c) {
		return userDao.queryAll(c);
	}

	public int executeHql(String hql) {
		return userDao.executeHql(hql);
	}

	public Page<UserInfo> query(String hql, Map<String, Object> params,
			Integer currentPage, Integer rows) {
		return userDao.query(hql, params, currentPage, rows);
	}

	public UserInfo getByNo(Class<UserInfo> c, String no) {
		return userDao.getByNo(c, no);
	}

	public UserInfo getByHql(String hql) {
		return userDao.getByHql(hql);
	}

	public void delete(UserInfo user) {
		userDao.delete(user);
	}

	public void update(UserInfo user) {
		userDao.update(user);
	}

	public void reset(Integer id) {
		userDao.executeHql("update UserInfo set password='202cb962ac59075b964b07152d234b70' where id="+id);
	}

	public RoleInfo getMaxRole(List<RoleInfo> roles) {
		RoleInfo role=roles.get(0);
		for (int i = 0; i < roles.size(); i++) {
			RoleInfo role1=roles.get(i);
			role=(role.getGrade()<role1.getGrade())? role1:role;
		}
		return role;
	}

	public Page<UserInfo> find(QueryFilter filter) {
		return userDao.find(filter);
	}

	public String queryOneField(String hql) {
		return userDao.queryOneField(hql);
	}

	 

}
