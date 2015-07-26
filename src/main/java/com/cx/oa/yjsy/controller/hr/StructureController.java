package com.cx.oa.yjsy.controller.hr;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cx.oa.yjsy.base.Page;
import com.cx.oa.yjsy.enitty.hr.StructureInfo;
import com.cx.oa.yjsy.enitty.hr.UserInfo;
import com.cx.oa.yjsy.service.hr.StructureService;
import com.cx.oa.yjsy.service.hr.UserService;
import com.cx.oa.yjsy.util.annotation.MethodName;

@Controller
@RequestMapping(value = "/structure")
public class StructureController {
	@Autowired
	private StructureService structureService;
	@Autowired
	private UserService userService;
	@MethodName(name="组织架构管理页面")
	@RequestMapping(value="show",method={RequestMethod.POST,RequestMethod.GET})
	public String page(){
		return "structureList";
	}
	@MethodName(name="使用组织架构")
	@RequestMapping(value="lookup",method={RequestMethod.POST,RequestMethod.GET})
	public String lookup(Model model){
		System.out.println("Structure lookup");
		List<StructureInfo> list=structureService.queryAll();
		Page<StructureInfo> page=new  Page<StructureInfo>();
		page.setData(list);
		model.addAttribute("pages", page);
		return "structureLookup";
	}
	@RequestMapping(value="list",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public List<StructureInfo> query(String all){
		List<StructureInfo> list=structureService.queryAll();
		if(all!=null){
			List<UserInfo> users=userService.queryAll(UserInfo.class);
			for(UserInfo u:users){
				list.add(new StructureInfo(u.getId(), u.getName(), u.getStructure().getId(),false,"images/diy/user.png"));
			}
		}
		return list;
	}
	@RequestMapping(value="save",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String save(Model model,StructureInfo structure){
		System.out.println(structure);
		structureService.save(structure);
		return "structureList";
	}
	@RequestMapping(value="update",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String update(Model model,StructureInfo structure){
		System.out.println(structure);
		structureService.update(structure);
		return "structureList";
	}
	@RequestMapping(value="delete",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String delete(Model model,StructureInfo structure){
		System.out.println(structure);
		structureService.delete(structure);
		return "structureList";
	}
	
}
