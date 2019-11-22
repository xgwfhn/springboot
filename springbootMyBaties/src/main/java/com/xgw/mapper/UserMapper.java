package com.xgw.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.SelectKey;

import com.xgw.model.User;

public interface UserMapper {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    int insertBatch(List<User> users);
    
 // 使用Option来对应着XML设置的select标签的属性，userGeneratordKeys表示要使用自增主键，keyProperty用来指定主键字段的字段名。
    /**
     * keyProperty: 表示将select返回值设置到该属性中
     * resultType: 返回类型
     * before: 是否在insert之前执行
     * statement: 自定义子查询
     * @param userBase
     */
    @SelectKey(keyProperty = "id",resultType = String.class, before = true,
            statement = "select sys_guid() from dual")
    @Options(keyProperty = "id", useGeneratedKeys = true)
    @Insert("insert into T_S_USER (ID,DEV_FLAG,EMP_NO,SEX,USERNAME) values (#{id},#{devFlag},#{empNo},#{sex},#{username})")//sql语句写在类中的形式
    int insert1(User user);//注解方式插入    
    
    
    @Insert(
            {"<script>",
               " INSERT ALL",
               "<foreach collection ='list' item='item'>",
               "into T_S_USER (ID,DEV_FLAG,EMP_NO,SEX,USERNAME) values  ((select sys_guid() as id from dual),#{item.devFlag},#{item.empNo},#{item.sex},#{item.username})",
               "</foreach>  SELECT * FROM dual",
               "</script>"
            }
       )
    int insertBatchByZj(List<User> users);//注解方式批量插入    
   
}