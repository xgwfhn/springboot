package com.xgw.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import com.xgw.mapper.RoleMapper;
import com.xgw.model.Role;
import com.xgw.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RedisTemplate<Object,Object> redisTemplate;
	
	@Autowired
	private RoleMapper roleMapper;
	
	
	//采用synchronized 解决高并发下  缓存穿透问题
	@Override
	public List<Role> getAllRole() {
		System.out.println("serviceDate:"+System.currentTimeMillis());
		List<Role> roles=(List<Role>) redisTemplate.opsForValue().get("allRoles");
		if(null==roles) {
			synchronized (this) {
				roles=(List<Role>) redisTemplate.opsForValue().get("allRoles");
				if(null==roles) {
					System.out.println("查询数据库--------------"); 
					roles=roleMapper.getAllRole();
					RedisSerializer stringSerializer = new StringRedisSerializer();
					redisTemplate.setKeySerializer(stringSerializer);//存储key时不乱码
			        //redisTemplate.setValueSerializer(stringSerializer);
					redisTemplate.opsForValue().set("allRoles", roles);
				}
			}
		}
		return roles;
	}

}
