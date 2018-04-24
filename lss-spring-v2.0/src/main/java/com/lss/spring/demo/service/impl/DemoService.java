package com.lss.spring.demo.service.impl;


import com.lss.spring.demo.service.IDemoService;
import com.lss.spring.framework.annotion.LssService;

@LssService
public class DemoService implements IDemoService {

	public String get(String name) {
		return "My name is " + name;
	}

}
