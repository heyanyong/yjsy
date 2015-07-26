package com.cx.oa.yjsy.dao.sys.impl;

import org.springframework.stereotype.Repository;

import com.cx.oa.yjsy.dao.BaseDao;
import com.cx.oa.yjsy.dao.sys.UrlDao;
import com.cx.oa.yjsy.enitty.sys.UrlInfo;

@Repository("urlDao")
public class UrlDaoImpl extends BaseDao<UrlInfo> implements UrlDao{
}
