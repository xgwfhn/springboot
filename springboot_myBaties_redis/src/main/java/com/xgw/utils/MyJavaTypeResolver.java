package com.xgw.utils;

import java.sql.Types;

import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.internal.types.JavaTypeResolverDefaultImpl;
import org.mybatis.generator.internal.types.JavaTypeResolverDefaultImpl.JdbcTypeInformation;

/**
 * @author lichuang
 * @since 2018-06-12
 *
 *        Mybatis生成代码类型自定义转换
 */
public class MyJavaTypeResolver extends JavaTypeResolverDefaultImpl {

	/**
	 * 将tinyint转换为Integer，这里是关键所在
	 */
	public MyJavaTypeResolver() {
		super();
		/*super.typeMap.put(-6, new JavaTypeResolverDefaultImpl.JdbcTypeInformation("VARCHAR2",
				new FullyQualifiedJavaType(String.class.getName())));*/
        super.typeMap.put(-6, new JavaTypeResolverDefaultImpl.JdbcTypeInformation("TINYINT", new FullyQualifiedJavaType(Integer.class.getName())));
     /*   super.typeMap.put(12, new JavaTypeResolverDefaultImpl.JdbcTypeInformation("VARCHAR", new FullyQualifiedJavaType(String.class
        	      .getName())));*/
        super.typeMap.put(Types.VARCHAR, new JdbcTypeInformation("VARCHAR", new FullyQualifiedJavaType(String.class.getName())));

	}
}
