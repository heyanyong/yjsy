package com.cx.oa.yjsy.service.hr.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cx.oa.yjsy.base.Page;
import com.cx.oa.yjsy.dao.hr.RoleDao;
import com.cx.oa.yjsy.enitty.hr.RoleInfo;
import com.cx.oa.yjsy.enitty.sys.UrlInfo;
import com.cx.oa.yjsy.service.hr.RoleService;

@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleDao roleDao;

	@Override
	public Serializable save(RoleInfo role) {
		return roleDao.save(role);
		
	}

	@Override
	public RoleInfo get(Class<RoleInfo> c, Serializable id) {
		 
		return roleDao.get(c, id);
	}

	@Override
	public void delete(RoleInfo role) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(RoleInfo role) {
		roleDao.update(role);
	}

	@Override
	public int executeHql(String hql) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int executeHql(String hql, Map<String, Object> params) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<RoleInfo> queryAll(Class<RoleInfo> c) {
		return roleDao.queryAll(c);
	}

	@Override
	public RoleInfo load(Class<RoleInfo> c, Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RoleInfo get(String hql, Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RoleInfo getObject(String hql, Object[] params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<RoleInfo> query(String hql, Map<String, Object> params,
			Integer currentPage, Integer rows) {
		return roleDao.query(hql, params, currentPage, rows);
	}

	@Override
	public List<UrlInfo> queryUrlAll(Class<UrlInfo> c) {
		return roleDao.queryUrlAll(c);
	}

}
