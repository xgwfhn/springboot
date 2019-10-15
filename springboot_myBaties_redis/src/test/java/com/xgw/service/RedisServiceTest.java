package com.xgw.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.xgw.service.RedisService;

@RunWith(SpringRunner.class)
@SpringBootTest
@SpringBootApplication
@MapperScan({"com.xgw.mapper"})
public class RedisServiceTest {
	
	@Autowired
	private RedisService redisService;

	@Test
	public  void  testRedis(){
	   redisService.setString("我的祖国","好久不见33");
	   String str= redisService.getString("我的祖国");
	   System.out.println(str);
	}
	
	@Test
	public  void  testRedis1(){
	   System.out.println("111");
	}
	
}
