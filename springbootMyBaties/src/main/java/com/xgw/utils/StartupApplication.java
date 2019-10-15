package com.xgw.utils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
 
@SpringBootApplication
@MapperScan({"com.xgw.mapper"})
@EnableTransactionManagement //开启事务
@ComponentScan(basePackages={"com.xgw.service","com.xgw.utils","com.xgw.controller"}) //指定要扫描的包  将包里的实体创建

public class StartupApplication {

	public static void main(String[] args) {
		SpringApplication.run(StartupApplication.class, args);
	}

}
