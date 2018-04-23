package com.lss.demo.service.impl;



import com.lss.demo.service.IDemoService;
import com.lss.spring.annotion.LssService;

@LssService
public class DemoService implements IDemoService {

	public String get(String name) {
		return "My name is " + name;
	}

}
