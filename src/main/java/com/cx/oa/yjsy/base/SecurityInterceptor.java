package com.cx.oa.yjsy.base;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.cx.oa.yjsy.enitty.hr.UserInfo;
import com.cx.oa.yjsy.enitty.sys.UrlInfo;


/**
 * 权限拦截器
 * 
 */
public class SecurityInterceptor implements HandlerInterceptor {
	//permissionUrls.add("/demoInitController/init.do");
	private List<String> includeRegExp;// 需要拦截的url（正则表达式）
	private List<String> excludeRegExp;// 不需要拦截的url

	public List<String> getExcludeRegExp() {
		return excludeRegExp;
	}

	public void setExcludeRegExp(List<String> excludeRegExp) {
		this.excludeRegExp = excludeRegExp;
	}

	public List<String> getIncludeRegExp() {
		return includeRegExp;
	}

	public void setIncludeRegExp(List<String> includeRegExp) {
		this.includeRegExp = includeRegExp;
	}

	/**
	 * 完成页面的render后调用
	 */
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception exception) throws Exception {

	}

	/**
	 * 在调用controller具体方法后拦截
	 */
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView modelAndView) throws Exception {

	}

	/**
	 * 在调用controller具体方法前拦截
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		String requestUri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String url = requestUri.substring(contextPath.length());
		if(url.contains(".")){
			return true;
		}
		
		for (String urlReg : excludeRegExp) {
			if (Pattern.compile(urlReg.trim()).matcher(url).find()) {
				return true;// 如果当前访问地址是不需要拦截的url，直接通过
			}
		}

		Boolean useCheck = false;// 当前访问地址是否需要权限验证
		for (String urlReg : includeRegExp) {
			if (Pattern.compile(urlReg.trim()).matcher(url).find()) {
				useCheck = true;
				break;
			}
		}
		if (useCheck) {// 看看session中的用户信息里面有没有包含这个url，如果没有就踢出去，没权限
			
			@SuppressWarnings("unchecked")
			List<UrlInfo> permisions = (List<UrlInfo>) request.getSession(false).getAttribute("permisions");
			String msg = "您没有权限访问【" + url + "】";
			if (permisions == null || permisions== null || permisions.size() < 1) {// 还可以加入其它条件
				return noAccess(request, response, msg);
			} else {
				if (permisions.contains(new UrlInfo(url))) {
					return true;
				}
				return noAccess(request, response, msg);
			}
		}
		return true;
	}

	/**
	 * 没权限踢出去
	 * 
	 * @param request
	 * @param response
	 * @param msg
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	private boolean noAccess(HttpServletRequest request, HttpServletResponse response, String msg) throws ServletException, IOException {
		UserInfo lu=(UserInfo) request.getSession().getAttribute("loginUser");
		System.out.println(lu.getName()+","+msg);
		if (request.getHeader("X-Requested-With") == null) {// 如果不是ajax请求，就返回一个页面
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("/error/noAccess.jsp").forward(request, response);
			return false;
		} else {// 是ajax请求，返回json格式的信息
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write("{\"message\":\"没有权限\",\"statusCode\":\"300\"}");
			response.getWriter().flush();
			response.getWriter().close();
			return false;
		}
	}
}
