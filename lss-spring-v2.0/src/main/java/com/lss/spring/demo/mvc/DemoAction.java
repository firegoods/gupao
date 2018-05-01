package com.lss.spring.demo.mvc;



import com.lss.spring.demo.service.IDemoService;
import com.lss.spring.framework.annotion.LssAutowried;
import com.lss.spring.framework.annotion.LssController;
import com.lss.spring.framework.annotion.LssRequestMapping;
import com.lss.spring.framework.annotion.LssRequestParam;
import com.lss.spring.framework.webmvc.LssModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@LssController
@LssRequestMapping("/demo")
public class DemoAction {
	
	@LssAutowried
    private IDemoService demoService;
	
	@LssRequestMapping("/query.json")
	public LssModelAndView query(HttpServletRequest req, HttpServletResponse resp,
								 @LssRequestParam("name") String name){
		String result = demoService.get(name);
		System.out.println(result);
//		try {
//			resp.getWriter().write(result);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		return out(resp,result);


	}
	
	@LssRequestMapping("/edit.json")
	public void edit(HttpServletRequest req,HttpServletResponse resp,Integer id){

	}

	private LssModelAndView out(HttpServletResponse response,String str){
		try {
			response.getWriter().write(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return  null;
	}
	
}
