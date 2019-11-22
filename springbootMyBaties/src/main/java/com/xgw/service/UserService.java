package com.xgw.service;

import java.util.List;

import com.xgw.model.User;

public interface UserService {

	public int  insert1(User user);  
	public int  insret(User user);
	public int insertSelective(User user);
	public   int insertBatch(List<User> users);  
	public   int insertBatchByZj(List<User> users);
	public int insertBatch1(List<User> users);  
	 public int insertBatch2(List<User> users);   
}
