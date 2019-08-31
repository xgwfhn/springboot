package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.config.ConfigInfo;
//自定义参数读取
@Controller
public class ConfigInfoCotroller {
	//value注解  方式读取自定义配置信息
	@Value("${boot.name}")
    private String name;
	
	@Autowired
	private  ConfigInfo configInfo;

	@ResponseBody
	@RequestMapping("/getName")
	public String getName() {
		return name;
	}
	
	//ConfigurationProperties 方式读取
	@ResponseBody
	@RequestMapping("/getNameByConfig")
	public String getNameByConfig() {
		return configInfo.getName();
	}
		
	
}
