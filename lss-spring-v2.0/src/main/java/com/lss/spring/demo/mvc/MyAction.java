package com.lss.spring.demo.mvc;



import com.lss.spring.demo.service.IModifyService;
import com.lss.spring.demo.service.IQueryService;
import com.lss.spring.framework.annotion.*;
import com.lss.spring.framework.webmvc.LssModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 公布接口url
 * @author Tom
 *
 */
@LssController
@LssRequestMapping("/web")
public class MyAction {

	@LssAutowried
	IQueryService queryService;
	@LssAutowried
	IModifyService modifyService;
	
	@LssRequestMapping("/query.json")
	public LssModelAndView query(HttpServletRequest request, HttpServletResponse response,
								 @LssRequestParam("name") String name){
		String result = queryService.query(name);
		//System.out.println(result);
		return out(response,result);
	}
	
	@LssRequestMapping("/add*.json")
	public LssModelAndView add(HttpServletRequest request,HttpServletResponse response,
			   @LssRequestParam("name") String name,@LssRequestParam("addr") String addr){
		String result = modifyService.add(name,addr);

		return out(response,result);
	}
	
	@LssRequestMapping("/remove.json")
	public LssModelAndView remove(HttpServletRequest request,HttpServletResponse response,
		   @LssRequestParam("id") Integer id){
		String result = modifyService.remove(id);
		return out(response,result);
	}
	
	@LssRequestMapping("/edit.json")
	public LssModelAndView edit(HttpServletRequest request,HttpServletResponse response,
			@LssRequestParam("id") Integer id,
			@LssRequestParam("name") String name){
		String result = modifyService.edit(id,name);
		return out(response,result);
	}
	
	
	
	private LssModelAndView out(HttpServletResponse resp,String str){
		try {
			resp.getWriter().write(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
