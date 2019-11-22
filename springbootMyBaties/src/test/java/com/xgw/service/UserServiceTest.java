package com.xgw.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.xgw.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
@SpringBootApplication
@MapperScan({"com.xgw.mapper"})
@EnableTransactionManagement //开启事务
public class UserServiceTest {

	@Autowired
	private UserService userService;
	
	@Test
	public void insert1UserTest() {//注解方式插入
		User u=new User();
		//u.setId("666666");
		u.setDevFlag("0");
		u.setAddress("海口市");
		u.setEmpNo("111111");
		u.setSex("1");
		u.setUsername("测试用户33");
		int result=userService.insert1(u);
		System.out.println(result);//返回成功插入的记录数
		System.out.println(u.getId());//返回插入的记录的主键ID
	}
	
	@Test
	public void insertSelectiveUserTest() {//配置方式插入
		User u=new User();
		u.setDevFlag("0");
		//u.setAddress("海口市");
		u.setEmpNo("2222");
		u.setSex("1");
		u.setUsername("测试用户22");
		u.setCreateDate(new Date());
		int result=userService.insertSelective(u);
		System.out.println(result);//返回成功插入的记录数
		System.out.println(u.getId());//返回插入的记录的主键ID
	}
	
	@Test
	public void insertBatchTest() {//配置方式批量插入
		List<User> list= new ArrayList<User>();
		for(int i=0;i<500;i++) {
			User u=new User();
			u.setDevFlag("0");
			//u.setAddress("海口市");
			u.setEmpNo("2222");
			u.setSex("1");
			u.setUsername("测试用户"+i);
			u.setCreateDate(new Date());
			list.add(u);
		}
		int result=userService.insertBatch(list);//返回成功插入的记录数
		System.out.println("result:"+result);
		/*for(User u:list) {
			System.out.println("id:"+u.getId());//成功插入获取不到ID
		}*/
	}
	
	
	@Test
	public void insertBatchByZjTest() {//注解方式批量插入
		List<User> list= new ArrayList<User>();
		for(int i=0;i<1000;i++) {//批量插入1000条就不行了   500条还勉强
			User u=new User();
			u.setDevFlag("0");
			//u.setAddress("海口市");
			u.setEmpNo("2222");
			u.setSex("1");
			u.setUsername("测试用户"+i);
			u.setCreateDate(new Date());
			list.add(u);
		}
		System.out.println("beginTime:"+new Date());
		int result=userService.insertBatchByZj(list);//返回成功插入的记录数
		System.out.println("result:"+result);

		System.out.println("endTime:"+new Date());
		/*for(User u:list) {
			System.out.println("id:"+u.getId());//成功插入获取不到ID
		}*/
	}

	@Test
	public void insertBatch1Test() {//注解方式批量插入
		List<User> list= new ArrayList<User>();
		for(int i=0;i<1000;i++) {//批量插入1000条就不行了   500条还勉强
			User u=new User();
			u.setDevFlag("0");
			//u.setAddress("海口市");
			u.setEmpNo("2222");
			u.setSex("1");
			u.setUsername("测试用户"+i);
			u.setCreateDate(new Date());
			list.add(u);
		}
		System.out.println("beginTime:"+new Date());
		int result=userService.insertBatch1(list);//返回成功插入的记录数
		System.out.println("result:"+result);

		System.out.println("endTime:"+new Date());
		
	}
	
	
	@Test
	public void insertBatch2Test() {//注解方式批量插入
		List<User> list= new ArrayList<User>();
		for(int i=0;i<8000;i++) {//批量插入1000条就不行了   500条还勉强
			User u=new User();
			u.setDevFlag("0");
			//u.setAddress("海口市");
			u.setEmpNo("2222");
			u.setSex("1");
			u.setUsername("测试用户"+i);
			u.setCreateDate(new Date());
			list.add(u);
		}
		System.out.println("beginTime:"+new Date());
		int result=userService.insertBatch2(list);//返回成功插入的记录数
		System.out.println("result:"+result);

		System.out.println("endTime:"+new Date());
		
	}
}
