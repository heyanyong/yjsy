package com.cx.oa.yjsy.service.sys;

import java.io.Serializable;
import java.util.Map;

import com.cx.oa.yjsy.base.Page;
import com.cx.oa.yjsy.enitty.sys.ControllerLog;



/**
 * ControllerLogService
 */
public interface ControllerLogService  {

	public Serializable save(ControllerLog controllerLog);
	public ControllerLog  get(Class<ControllerLog> c, Serializable id);
	public Page<ControllerLog> query(String hql, Map<String, Object> params,
			Integer currentPage, Integer rows);

}