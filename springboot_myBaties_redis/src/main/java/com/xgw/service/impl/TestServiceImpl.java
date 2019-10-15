package com.xgw.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xgw.model.Test;
import com.xgw.service.TestService;

@Service
public class TestServiceImpl implements TestService {
	
    /*@Autowired
	private TestMapper testMapper;*/
    
	@Override
	public List<Test> getAllTest() {
	//	return testMapper.getAllTest();
		return null;
	}
	
	@Transactional//加入事务
	@Override
	public int  update() {
		Test test=new Test ();
		test.setId("444");
		test.setAid("777777");
		test.setTid("555");
		//int result=testMapper.updateByPrimaryKeySelective(test);
		//int result=testMapper.insert(test);

		//int i=10/0;
		return 0;
	}

}
