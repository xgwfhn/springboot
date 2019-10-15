package com.xgw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xgw.service.RedisService;

@Controller
public class RedisController {

	@Autowired
	private RedisService redisService;
	
	@ResponseBody
	@RequestMapping("/getRole")
	public void  getRole() {
		   redisService.setString("dashabi","好久不见");
		   String str= redisService.getString("dashabi");
		   System.out.println(str);
	}
	
}
