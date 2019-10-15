package com.xgw.mapper;

import java.util.List;

import com.xgw.model.Role;

public interface RoleMapper {
    int deleteByPrimaryKey(Object id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Object id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
    List<Role> getAllRole();
}