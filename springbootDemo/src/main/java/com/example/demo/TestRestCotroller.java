package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.config.ConfigInfo;
//@RestController注解相当于@ResponseBody ＋ @Controller合在一起的作用   返回json或string字符串
@RestController
public class TestRestCotroller {


	@RequestMapping("/getName1")
	public String getName1() {
		return "zhangsan";
	}
	
	@RequestMapping("/getConfigInfo")
	public ConfigInfo getConfigInfo() {
		ConfigInfo c=new ConfigInfo();
		c.setAge("1");
		c.setName("lisi");
		return c;
	}
	
}
