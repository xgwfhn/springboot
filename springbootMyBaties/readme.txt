1遇到问题   在eclipse安装mybatis-generator插件后   使用它生成没有反应   代码没有生成
2使用java程序或运行 mybatis-generator 命令  生成的代码没有注释  及实体的属性类型 没有和数据库类型对应上 如数据库的字符串类型    实体的属性却变成object
3数据库字段 对应注释 没有生成
错误
Caused by: java.lang.IllegalArgumentException: Result Maps collection already contains value for com.xgw.mapper.RoleMapper.BaseResultMap
解决 https://blog.csdn.net/qq_41378597/article/details/83900060

RoleMapper.xml  在采用mybatis-generator 生成代码前 要先将映射文件 删除  否则会在此文件上 累加 生成的代码  造成启动报错 