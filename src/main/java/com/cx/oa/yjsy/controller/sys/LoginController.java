package com.cx.oa.yjsy.controller.sys;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cx.oa.yjsy.enitty.hr.RoleInfo;
import com.cx.oa.yjsy.enitty.hr.UserInfo;
import com.cx.oa.yjsy.enitty.sys.Json;
import com.cx.oa.yjsy.enitty.sys.UrlInfo;
import com.cx.oa.yjsy.service.hr.UserService;
import com.cx.oa.yjsy.util.annotation.MethodName;

@Controller
public class LoginController {
	@Autowired
	private UserService userService;
//		
	@MethodName(name="访问主页")
	@RequestMapping(value="index")
	public String index(Model m,HttpSession session,String  pageNum){
		return "welcome";
	}
	@RequestMapping(value="login")
	public String LoginPage(){
		return "login";
	}
	
	@RequestMapping(value="logout")
	public String Logout(HttpSession session){
		session.removeAttribute("loginUser");
		session.invalidate();
		return "redirect:/login.jsp";
	}
	@MethodName(name="登录验证")
	@RequestMapping(value="/checkLogin", method=RequestMethod.POST)
	public String checkLogin(UserInfo user , HttpServletRequest request){
		UserInfo loginUser=userService.checkLogin(user);
		if(loginUser==null){
			return "redirect:/login.jsp?loginMsg=1";
		}else{
			HttpSession session=request.getSession();
			session.setAttribute("loginUser", loginUser);
			List<RoleInfo> roles=loginUser.getRoles();
			List<UrlInfo> permisions=new ArrayList<UrlInfo>();
			for(RoleInfo role:roles){
				List<UrlInfo> urls=role.getUrls();
				if(urls!=null){
					permisions.addAll(urls);
				}
			}
			for(UrlInfo ui:permisions){
				String url=ui.getUrl();
				if((!url.contains("/"))&&url.contains("_")){
					session.setAttribute(url, url);
				}
			}
			session.setAttribute("permisions", permisions);
			return "redirect:/index";
		}
	}
	@MethodName(name="超时登录验证")
	@RequestMapping(value="/checkLoginDialog", method=RequestMethod.POST)
	@ResponseBody
	public Json checkLoginDialog(UserInfo user , HttpServletRequest request){
		UserInfo loginUser=userService.checkLogin(user);
		if(loginUser==null){
			return new Json("登陆失败", "300", null, null, null, null);
		}else{
			HttpSession session=request.getSession();
			session.setAttribute("loginUser", loginUser);
			List<RoleInfo> roles=loginUser.getRoles();
			List<UrlInfo> permisions=new ArrayList<UrlInfo>();
			for(RoleInfo role:roles){
				List<UrlInfo> urls=role.getUrls();
				if(urls!=null){
					permisions.addAll(urls);
				}
			}
			for(UrlInfo ui:permisions){
				String url=ui.getUrl();
				if((!url.contains("/"))&&url.contains("_")){
					session.setAttribute(url, url);
				}
			}
			session.setAttribute("permisions", permisions);
			return new Json(null, "200", null, null, "closeCurrent", null);
		}
	}
}
