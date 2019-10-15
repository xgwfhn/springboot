package com.xgw.mapper;

import java.util.List;

import com.xgw.model.Test;
import com.xgw.model.TestKey;

public interface TestMapper {
    int deleteByPrimaryKey(TestKey key);

    int insert(Test record);

    int insertSelective(Test record);

    Test selectByPrimaryKey(TestKey key);

    int updateByPrimaryKeySelective(Test record);

    int updateByPrimaryKey(Test record);
    List<Test> getAllTest();
}