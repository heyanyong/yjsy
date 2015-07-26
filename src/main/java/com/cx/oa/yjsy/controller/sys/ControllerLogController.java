package com.cx.oa.yjsy.controller.sys;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cx.oa.yjsy.base.Page;
import com.cx.oa.yjsy.enitty.sys.ControllerLog;
import com.cx.oa.yjsy.service.sys.ControllerLogService;



/**
 * ControllerLog控制器
 *
 */
@Controller
@RequestMapping("/controllerLog")
public class ControllerLogController {

	 @Autowired
	 private ControllerLogService controllerLogService ;

	@RequestMapping("/get/{id}")
	public String  get(@PathVariable Long id, HttpServletRequest request) {
		ControllerLog info=controllerLogService.get(ControllerLog.class, id);
		request.setAttribute("info", info);
		return "sys/controllerLogDetail";
	}

	@RequestMapping("/list")
	public String  logList(Model m,Integer pageNum) {
		Page<ControllerLog> pages= controllerLogService.query("", null, pageNum, 17);
		m.addAttribute("pages", pages);
		return "sys/controllerLogList";
	}

}
