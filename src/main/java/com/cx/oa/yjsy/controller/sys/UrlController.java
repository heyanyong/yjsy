package com.cx.oa.yjsy.controller.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cx.oa.yjsy.base.Page;
import com.cx.oa.yjsy.enitty.sys.Json;
import com.cx.oa.yjsy.enitty.sys.UrlInfo;
import com.cx.oa.yjsy.service.sys.UrlService;
import com.cx.oa.yjsy.util.annotation.MethodName;


@Controller
@RequestMapping(value = "/sysurl")
public class UrlController {
	@Autowired
	private UrlService urlService;
	
	@MethodName(name="保存一个URL")
	@RequestMapping(value = "/save")
	@ResponseBody
	public Json save(UrlInfo url) {
		urlService.save(url);
		return new Json("操作成功","200","sysurlList","sysurlList","closeCurrent",null);
	}
	@MethodName(name="删除一个URL")
	@RequestMapping(value = "/delete/{id}")
	@ResponseBody
	public Json delete(@PathVariable Integer id) {
		urlService.delete(new UrlInfo(id));
		return new Json("操作成功","200","sysurlList","sysurlList",null,null);
	}
	@MethodName(name="更新一个URL")
	@RequestMapping(value = "/update",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Json update(UrlInfo url) {
		urlService.update(url);
		return new Json("操作成功","200","sysurlList","sysurlList",null,null);
	}
	@MethodName(name="查看一个URL")
	@RequestMapping(value = "/edit/{id}")
	public String edit(@PathVariable Integer id,Model m) {
		UrlInfo url=urlService.get(UrlInfo.class, id);
		m.addAttribute("info", url);
		return "sys/urlDetail";
	}
	 
	@MethodName(name="URL列表")
	@RequestMapping(value="list",method={RequestMethod.POST,RequestMethod.GET})
	public String query(Model model,Integer pageNum){
		Page<UrlInfo> pages=urlService.query("from UrlInfo", null, pageNum, 17);
		model.addAttribute("pages", pages);
		return "sys/urlList";
	}
	 
}
