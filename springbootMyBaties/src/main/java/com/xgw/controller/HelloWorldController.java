package com.xgw.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.xgw.service.TestService;

import oracle.sql.BLOB;

@Controller
public class HelloWorldController {
    @Autowired
	private TestService serive;
	
	//页面跳转   页面必须在/src/main/webapp/下
	@RequestMapping("/info")
	public String info() {
		System.out.println("1111111111");
		return "aa";//如果要省略后缀  则src/main/resources/application.properties 配置
	}
	
	
	@ResponseBody
	@RequestMapping("/getTest")
	public Object ret() {
		return serive.getAllTest();//如果要省略后缀  则src/main/resources/application.properties 配置
	}
	
	
	@ResponseBody
	@RequestMapping("/update")
	public Object update() {
		return serive.update();//如果要省略后缀  则src/main/resources/application.properties 配置
	}
	
	
	

}
