package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.config.ConfigInfo;
//@GetController注解相当于@@RequestMapping ＋ get请求
@RestController
public class TestGetCotroller {

    //以下2个方法是等价的
	@RequestMapping(value="/getConfigInfoByGet",method=RequestMethod.GET)
	public ConfigInfo getConfigInfoByGet() {
		ConfigInfo c=new ConfigInfo();
		c.setAge("1");
		c.setName("lisi");
		return c;
	}
	
	@GetMapping("/getConfigInfoByGet1")
	public ConfigInfo getConfigInfoByGet1() {
		ConfigInfo c=new ConfigInfo();
		c.setAge("1");
		c.setName("lisi");
		return c;
	}
	
}
