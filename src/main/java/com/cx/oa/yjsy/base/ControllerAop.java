package com.cx.oa.yjsy.base;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.cx.oa.yjsy.enitty.hr.UserInfo;
import com.cx.oa.yjsy.enitty.sys.ControllerLog;
import com.cx.oa.yjsy.service.sys.ControllerLogService;
import com.cx.oa.yjsy.util.IpUtil;
import com.cx.oa.yjsy.util.JsonPropertyFilter;
import com.cx.oa.yjsy.util.annotation.MethodName;

/**
 * 用于拦截所有@RequestMapping注解的方法，也就是Controller的方法
 * 
 * 将方法名称、传递的参数、返回值、IP等等信息存放在数据库表中
 * 
 * @author  
 *
 */
@Aspect
@Component
public class ControllerAop {

	@Resource(name = "controllerLogService")
	private ControllerLogService controllerLogService;

	@AfterReturning(value = "@annotation(org.springframework.web.bind.annotation.RequestMapping)", argNames = "returnValue", returning = "returnValue")
	public void afterInsertMethod(JoinPoint joinPoint, Object returnValue) {
		Signature signature = joinPoint.getSignature();

		if (signature.getDeclaringTypeName().equals("com.gxuts.wss.dms.controller.sys.ControllerLogController")) {// 这个类的方法不需要记录日志
			return;
		}

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		// HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
		UserInfo user= (UserInfo) request.getSession().getAttribute("loginUser");
		
		ControllerLog controllerLog = new ControllerLog();
		if(user!=null){
			controllerLog.setUserName(user.getName());
			controllerLog.setUserNo(user.getNo());
		}else{
			controllerLog.setUserName("未登录用户");
			controllerLog.setUserNo("未登录用户");
		}
		controllerLog.setIp(IpUtil.getIp(request));// 哪个IP访问的方法

		// System.out.println("类型：" + signature.getDeclaringType());
		// System.out.println("类型名" + signature.getDeclaringTypeName());
		controllerLog.setClassName(signature.getDeclaringTypeName());

		// System.out.println("修改器：" + signature.getModifiers());
		// System.out.println("方法名称:" + signature.getName());
		controllerLog.setMethodName(signature.getName());
		// System.out.println("方法全名：" + signature.toLongString());
		controllerLog.setMethodFullName(signature.toLongString().replaceFirst("\\(.*\\)", ""));
		// System.out.println("方法短名：" + signature.toShortString());

		MethodSignature methodSignature = (MethodSignature) signature;
		Method method = methodSignature.getMethod();
		MethodName methodNameAnnotation = method.getAnnotation(MethodName.class);
		if (methodNameAnnotation != null) {
			// System.out.println("中文方法名：" + methodNameAnnotation.name());
			controllerLog.setMethodCnName(methodNameAnnotation.name());
		} else {
			controllerLog.setMethodCnName("未命名方法");
		}

		List<Object> argsList = new ArrayList<Object>();
		JsonPropertyFilter filter = new JsonPropertyFilter();
		for (int i = 0; i < joinPoint.getArgs().length; i++) {
			Object arg = joinPoint.getArgs()[i];
			if (null != arg) {
				// String argClassName = arg.getClass().getSimpleName();
				if (arg instanceof HttpServletResponse) {
					argsList.add("HttpServletResponse"); 
				} else if (arg instanceof HttpServletRequest) {
					argsList.add("HttpServletRequest");
				} else if (arg instanceof HttpSession) {
					argsList.add("HttpSession");
				} else {
					argsList.add(arg);
				}
			}
		}
		// System.out.println("参数：" + JSON.toJSONString(argsList, filter, SerializerFeature.WriteDateUseDateFormat, SerializerFeature.DisableCircularReferenceDetect));
		//controllerLog.setArgsContent(JSON.toJSONString(argsList, filter, SerializerFeature.WriteDateUseDateFormat, SerializerFeature.DisableCircularReferenceDetect));
		// System.out.println("返回值：" + JSON.toJSONString(returnValue, filter, SerializerFeature.WriteDateUseDateFormat, SerializerFeature.DisableCircularReferenceDetect));
		//controllerLog.setReturnValue(JSON.toJSONString(returnValue, filter, SerializerFeature.WriteDateUseDateFormat, SerializerFeature.DisableCircularReferenceDetect));
		controllerLogService.save(controllerLog);
	}

}
