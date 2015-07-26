package com.cx.oa.yjsy.service.hr.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cx.oa.yjsy.base.Page;
import com.cx.oa.yjsy.dao.hr.StructureDao;
import com.cx.oa.yjsy.enitty.hr.StructureInfo;
import com.cx.oa.yjsy.service.hr.StructureService;

@Service("structureService")
@Transactional
public class StructureServiceImpl implements StructureService {
	@Autowired
	private StructureDao structureDao;

	@Override
	public Serializable save(StructureInfo structure) {
		return structureDao.save(structure);
		
	}


	@Override
	public StructureInfo get(Class<StructureInfo> c, Serializable id) {
		return null;
	}

	@Override
	public StructureInfo get(String hql, Map<String, Object> params) {
		return null;
	}

	@Override
	public StructureInfo load(Class<StructureInfo> c, Serializable id) {
		return null;
	}

	@Override
	public StructureInfo getObject(String hql, Object[] params) {
		return null;
	}

	@Override
	public int executeHql(String hql) {
		return 0;
	}

	@Override
	public List<StructureInfo> queryAll() {
		return structureDao.queryAll(StructureInfo.class);
	}

	@Override
	public Page<StructureInfo> query(String hql, Map<String, Object> params,
			Integer currentPage, Integer rows) {
		return structureDao.query(hql, params, currentPage, rows);
	}

	@Override
	public StructureInfo getByNo(Class<StructureInfo> c, String no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StructureInfo getByHql(String hql) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(StructureInfo structure) {
		structureDao.delete(structure);
	}

	@Override
	public void update(StructureInfo structure) {
		structureDao.update(structure);
	}

}
