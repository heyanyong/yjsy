package com.cx.oa.yjsy.service.hr;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.cx.oa.yjsy.base.Page;
import com.cx.oa.yjsy.enitty.hr.StructureInfo;


public interface StructureService {
	public Serializable save(StructureInfo structure);
	public StructureInfo get(Class<StructureInfo> c, Serializable id);
	public StructureInfo get(String hql, Map<String, Object> params);
	public StructureInfo load(Class<StructureInfo> c, Serializable id);
	public StructureInfo getObject(String hql,  Object[] params);
	public int executeHql(String hql);
	public List<StructureInfo> queryAll();
	public Page<StructureInfo> query(String hql, Map<String, Object> params, Integer currentPage, Integer rows) ;
	public StructureInfo getByNo(Class<StructureInfo> c, String no);  
	public StructureInfo getByHql(String hql);
	public void delete(StructureInfo structure);
	public void update(StructureInfo structure);
}
