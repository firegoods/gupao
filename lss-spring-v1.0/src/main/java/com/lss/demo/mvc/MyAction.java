package com.lss.demo.mvc;


import com.lss.demo.service.IDemoService;
import com.lss.spring.annotion.LssAutowried;
import com.lss.spring.annotion.LssController;
import com.lss.spring.annotion.LssRequestMapping;

@LssController
public class MyAction {

		@LssAutowried IDemoService demoService;
	
		@LssRequestMapping("/index.html")
		public void query(){

		}
	
}
