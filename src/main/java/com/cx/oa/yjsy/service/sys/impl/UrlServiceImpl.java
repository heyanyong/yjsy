package com.cx.oa.yjsy.service.sys.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cx.oa.yjsy.base.Page;
import com.cx.oa.yjsy.dao.sys.UrlDao;
import com.cx.oa.yjsy.enitty.sys.UrlInfo;
import com.cx.oa.yjsy.service.sys.UrlService;

@Service("urlService")
@Transactional
public class UrlServiceImpl implements UrlService {
	@Autowired
	private UrlDao urlDao;

	@Override
	public Serializable save(UrlInfo url) {
		return urlDao.save(url);
	}

	@Override
	public UrlInfo get(Class<UrlInfo> c, Serializable id) {
		return null;
	}

	@Override
	public List<UrlInfo> queryAll(Class<UrlInfo> c) {
		return urlDao.queryAll(c);
	}

	@Override
	public Page<UrlInfo> query(String hql, Map<String, Object> params,
			Integer currentPage, Integer rows) {
		return urlDao.query(hql, params, currentPage, rows);
	}

	@Override
	public void delete(UrlInfo url) {
		urlDao.delete(url);
	}

	@Override
	public void update(UrlInfo url) {
		urlDao.update(url);
	}
	 

}
