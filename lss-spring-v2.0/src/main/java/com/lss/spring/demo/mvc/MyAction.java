package com.lss.spring.demo.mvc;


import com.lss.spring.demo.service.IDemoService;
import com.lss.spring.framework.annotion.LssAutowried;
import com.lss.spring.framework.annotion.LssController;
import com.lss.spring.framework.annotion.LssRequestMapping;

@LssController
public class MyAction {

		@LssAutowried
		IDemoService demoService;
	
		@LssRequestMapping("/index.html")
		public void query(){

		}
	
}
