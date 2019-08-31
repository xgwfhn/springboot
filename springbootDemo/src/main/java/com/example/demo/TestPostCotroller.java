package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.config.ConfigInfo;
//@PostMapping注解相当于@@RequestMapping ＋ post请求
@RestController
public class TestPostCotroller {

    //以下2个方法是等价的
	@RequestMapping(value="/getConfigInfoByPost",method=RequestMethod.POST)
	public ConfigInfo getConfigInfoByPost() {
		ConfigInfo c=new ConfigInfo();
		c.setAge("1");
		c.setName("lisi");
		return c;
	}
	
	@PostMapping("/getConfigInfoByPost1")
	public ConfigInfo getConfigInfoByPost1() {
		ConfigInfo c=new ConfigInfo();
		c.setAge("1");
		c.setName("lisi");
		return c;
	}
	
}
