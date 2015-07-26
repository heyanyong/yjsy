package com.cx.oa.yjsy.dao.sys.impl;

import org.springframework.stereotype.Repository;

import com.cx.oa.yjsy.dao.BaseDao;
import com.cx.oa.yjsy.dao.sys.ControllerLogDao;
import com.cx.oa.yjsy.enitty.sys.ControllerLog;

@Repository("controllerLogDao")
public class ControllerLogDaoImpl extends BaseDao<ControllerLog> implements ControllerLogDao {
}
