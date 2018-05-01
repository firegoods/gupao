package com.lss.spring.demo.mvc;


import com.lss.spring.demo.service.IQueryService;
import com.lss.spring.framework.annotion.LssAutowried;
import com.lss.spring.framework.annotion.LssController;
import com.lss.spring.framework.annotion.LssRequestMapping;
import com.lss.spring.framework.annotion.LssRequestParam;
import com.lss.spring.framework.webmvc.LssModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * 公布接口url
 * @author Tom
 *
 */
@LssController
@LssRequestMapping("/")
public class PageAction {

	@LssAutowried
	IQueryService queryService;
	
	@LssRequestMapping("/first.html")
	public LssModelAndView query(@LssRequestParam("teacher") String teacher){
		String result = queryService.query(teacher);
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("teacher", teacher);
		model.put("data", result);
		model.put("token", "123456");
		return new LssModelAndView("first.html",model);
	}
	
}
