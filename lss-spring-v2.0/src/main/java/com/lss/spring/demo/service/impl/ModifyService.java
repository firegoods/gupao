package com.lss.spring.demo.service.impl;


import com.lss.spring.demo.service.IModifyService;
import com.lss.spring.framework.annotion.LssService;

/**
 * 增删改业务
 * @author Tom
 *
 */
@LssService
public class ModifyService implements IModifyService {

	/**
	 * 增加
	 */
	public String add(String name,String addr) {
		System.out.println("ModifyService.add");
		return "modifyService add,name=" + name + ",addr=" + addr;
	}

	/**
	 * 修改
	 */
	public String edit(Integer id,String name) {
		return "modifyService edit,id=" + id + ",name=" + name;
	}

	/**
	 * 删除
	 */
	public String remove(Integer id) {
		return "modifyService id=" + id;
	}
	
}
