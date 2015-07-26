package com.cx.oa.yjsy.dao.hr.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cx.oa.yjsy.dao.BaseDao;
import com.cx.oa.yjsy.dao.hr.RoleDao;
import com.cx.oa.yjsy.enitty.hr.RoleInfo;
import com.cx.oa.yjsy.enitty.sys.UrlInfo;

@Repository("roleDao")
public class RoleDaoImpl extends BaseDao<RoleInfo> implements RoleDao{

	public List<UrlInfo> queryUrlAll(Class<UrlInfo> c) {
		@SuppressWarnings("unchecked")
		List<UrlInfo> list=this.getSession().createCriteria(c).list();
		return list;
	}

 

}
