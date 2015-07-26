package com.cx.oa.yjsy.service.hr;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.cx.oa.yjsy.base.Page;
import com.cx.oa.yjsy.enitty.hr.RoleInfo;
import com.cx.oa.yjsy.enitty.hr.UserInfo;
import com.cx.oa.yjsy.util.QueryFilter;


 

public interface UserService {
	public Serializable save(UserInfo user);
	public UserInfo get(Class<UserInfo> c, Serializable id);
	public UserInfo get(String hql, Map<String, Object> params);
	public UserInfo load(Class<UserInfo> c, Serializable id);
	public UserInfo getObject(String hql,  Object[] params);
	public int executeHql(String hql);
	public UserInfo checkLogin(UserInfo user);
	public List<UserInfo> queryAll(Class<UserInfo> c);
	public Page<UserInfo> query(String hql, Map<String, Object> params, Integer currentPage, Integer rows) ;
	public UserInfo getByNo(Class<UserInfo> c, String no);
	public UserInfo getByHql(String hql);
	public void delete(UserInfo user);
	public void update(UserInfo user);
	public void reset(Integer id);
	public RoleInfo getMaxRole(List<RoleInfo> roles);
	public Page<UserInfo> find(QueryFilter filter);
	public String queryOneField(String hql);
}
