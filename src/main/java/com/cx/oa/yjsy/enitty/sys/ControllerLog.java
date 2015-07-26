package com.cx.oa.yjsy.enitty.sys;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 记录所有Controller访问日志
 * 
 */
@Entity
@DynamicInsert(true)
@DynamicUpdate(true)
public class ControllerLog implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String className;// 类名
	private String methodName;// 方法名
	private String methodFullName;// 方法全名
	private String methodCnName;// 中文方法名
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private String argsContent;// 参数内容
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private String returnValue;// 返回值
	private String ip;
	private String userName;// 操作用户
	private String userNo;// 操作用户
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date createDate = new Date();

	public String getUserName() {
		return userName;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getMethodFullName() {
		return methodFullName;
	}

	public void setMethodFullName(String methodFullName) {
		this.methodFullName = methodFullName;
	}

	public String getMethodCnName() {
		return methodCnName;
	}

	public void setMethodCnName(String methodCnName) {
		this.methodCnName = methodCnName;
	}

	public String getArgsContent() {
		return argsContent;
	}

	public void setArgsContent(String argsContent) {
		this.argsContent = argsContent;
	}

	public String getReturnValue() {
		return returnValue;
	}

	public void setReturnValue(String returnValue) {
		this.returnValue = returnValue;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	 

}
