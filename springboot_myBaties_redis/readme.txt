
eclipse 运行mybatis-generato 插件命令 mybatis-generator:generate -X

1遇到问题   在eclipse安装mybatis-generator插件后   使用它生成没有反应   代码没有生成
2使用java程序或运行 mybatis-generator 命令  生成的代码没有注释  及实体的属性类型 没有和数据库类型对应上 如数据库的字符串类型    实体的属性却变成object
3数据库字段 对应注释 没有生成

错误

--mybatis-generator
1 Caused by: java.lang.IllegalArgumentException: Result Maps collection already contains value for com.xgw.mapper.RoleMapper.BaseResultMap
  https://blog.csdn.net/qq_41378597/article/details/83900060(测试通过)
2 RoleMapper.xml  在采用mybatis-generator 生成代码前 要先将映射文件 删除  否则会在此文件上 累加 生成的代码  造成启动报错 
3 mybatis-generator配置详情
  https://blog.csdn.net/weixin_33774883/article/details/91382967
--mybatis-generator
  
--junit
4 [main] DEBUG org.springframework.test.context.support.ActiveProfilesUtils - Could not find an 'annotation declaring class' for annotation type [org.springframework.test.context.ActiveProfiles] and class [redis.RedisServiceTest]
     *添加@RunWith(SpringRunner.class)
		@SpringBootTest
		@SpringBootApplication
	 *  将将入口类和测试类所在包调整为一致即可  如 com.xgw.service.RedisService.java,那么该类的对应的测试包也要在com.xgw.service
5 java.lang.IllegalStateException: Unable to find a @SpringBootConfiguration, you need to use @ContextConfiguration or @SpringBootTest(classes=...) with your test
       解决  https://blog.csdn.net/IndexMan/article/details/85985714
--junit

-- redis
    *redis 不能远程连接  本地可以连接
	        解决 https://blog.csdn.net/qq_36470507/article/details/78874372 
	        将redis.windows,redis.windows.conf 的 protected-mode改为no,bind 127.0.0.1改为bind 0.0.0.0
                      重启服务redis-server.exe redis.windows.conf  另外需要在redis服务的机子将6379端口放开(新建入站规则),如果是百度云或阿里云  需要在安全组 将6389放开
    *修改redis登录密码
      https://blog.csdn.net/qq_27384769/article/details/84961406
    *redis 服务端及客户端下载    百度云上  
    *高并发下 redis缓存穿透问题,即保证 如当10000个同时请求,只能一个请求去查询数据 并把数据放置到redis缓存中
    *Redis key 乱码问题（springboot） 解决 https://blog.csdn.net/qq_31482599/article/details/86716454
-- redis

--postman
	postman批量测试 https://blog.csdn.net/aoyangang9708/article/details/101662204
--postman
--maven
	* install 时会自动执行junit里的方法  解决:https://blog.csdn.net/qq_30553235/article/details/77187958
	  
--maven
功能要点 
1 端口,上下文的配置，映射文件,数据库连接等配置
2 热部署
3 中文乱码
4 mybatis-generator 代码自动生成    数据库注释生成(未实现)  自定义java实体类型(未实现)  数据库字段类型是string生成的java实体字段类型确实Object
5 页面跳转
6 通过http访问 项目  获取json字符串
7 IDEA解决Maven项目编译后classes文件中没有.xml,JSP问题,eclipse可以不用配置 -
8 Mybatis Generator插件自动生成xml映射文件追加与覆盖的问题
      追加 解决 https://blog.csdn.net/u010696826/article/details/86306113(测试通过)  使用maven命令安装jar包到本地maven仓库  https://blog.csdn.net/gaoxir/article/details/99539501
     覆盖解决   1 https://blog.csdn.net/zdb1314/article/details/79256128(xml可以,但是java代码页覆盖了)  2 在  pom.xml <configuration> 配置 <overwrite>false</overwrite>(测试通过,但是会有备份文件) https://segmentfault.com/a/1190000013038272
9 通过 schema 指定不同的表的mapper.java和xml 存放到不同的包里  <table schema="jeecgos"  jeecgos为连接数据库的用户名
10 自动生成的代码只有insert方法，没有select,update等方法，提示Cannot obtain primary key information from the database, generated objects may be incomplete
          表中没有主键  添加主键后 即可(测试通过)
11 Could not write metadata for '/Servers'. 
   https://blog.csdn.net/qq_32332777/article/details/52836218(测试通过)
12 添加表主键 报    ORA-00955: 名称已由现有对象使用
   https://blog.csdn.net/WQSHYH226/article/details/16961635(测试通过) 前提要删除所有记录  因为设置初始值后  主键的值就相同了
13 Consider defining a bean of type 'com.xgw.mapper.TestMapper' in your configuration.  前提是 TestMapper已经添加@Mapper 但是还找不到
          在springboot 启动类中添加mapper包扫描  @MapperScan({"com.xgw.mapper"})  mapper中可以不用添加@Mapper注解(测试通过)
14 Registered driver with driverClassName=oracle.jdbc.driver.OracleDriver was not found, trying direct instantiation.
   https://www.cnblogs.com/6324/p/10903960.html(测试通过)
15 java.sql.SQLException: ORA-01017: invalid username/password; logon denied  前提 用户名和密码是对的  却报不正确
   application.properties 中将spring.datasource.username写成spring.datasource.name
16 提供springboot事务的支持
17 集成redis  支持redis key 友好可读性的序列化(key 没有乱码)
18 封装RedisTemplate的RedisUtil类   
计划要点
Spring Boot集成MyBatis分页插件_pagehelper