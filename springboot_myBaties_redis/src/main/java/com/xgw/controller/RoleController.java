package com.xgw.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xgw.service.RoleService;

@Controller
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	@ResponseBody
	@RequestMapping("/getAllRole")
	public Object  getAllRole() {
		System.out.println("Controller Date:"+System.currentTimeMillis()); 
		return roleService.getAllRole();
	}
}
