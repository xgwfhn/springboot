package com.xgw.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xgw.mapper.UserMapper;
import com.xgw.model.User;
import com.xgw.service.UserService;

@Service
public class UserServiceImpl  implements UserService{

	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	@Autowired
	private UserMapper userMapper;
	
	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;

	public SqlSessionTemplate getSqlSessionTemplate() 
	{
	    return sqlSessionTemplate;
	}

	
	@Override
	public int insert1(User user) {
		return userMapper.insert1(user); 
	}

	@Override
	public int insret(User user) {
		return userMapper.insert(user);
	}

	@Override
	public int insertSelective(User user) {
		 return userMapper.insertSelective(user);
	}

	@Override
	public int insertBatch(List<User> users) {
		return userMapper.insertBatch(users);
	}

	@Override
	public int insertBatchByZj(List<User> users) {
		return userMapper.insertBatchByZj(users);
	}
	
	
	@Transactional
	public int insertBatch1(List<User> users) {
	  int rcount=0;
	  SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
	  UserMapper mapper = sqlSession.getMapper(UserMapper.class);
			for (int i = 0; i < users.size(); i++) {
				int count=mapper.insertSelective(users.get(i));
				rcount=rcount+count;
				if(i%200==0) {
					sqlSession.flushStatements();
				}
			}
			
			//sqlSession.flushStatements();//sqlSession.flushStatements();起到一种预插入的作用(执行了这行代码之后,要插入的数据会锁定数据库的一行记录,并把数据库默认返回的主键赋值给插入的对象,这样就可以把该对象的主键赋值给其他需要的对象中去了)
	  return rcount;
	}
	
	//@Override
    public int insertBatch2(List<User> users){
        // TODO Auto-generated method stub
        int result = 1;
        int batchLastIndex=0;
        SqlSession batchSqlSession = null;
        try {
            batchSqlSession = this.getSqlSessionTemplate()
                    .getSqlSessionFactory()
                    .openSession(ExecutorType.BATCH, false);// 获取批量方式的sqlsession
            int batchCount = 500;// 每批commit的个数
            batchLastIndex = batchCount;// 每批最后一个的下标
            for (int index = 0; index < users.size();) {
                if (batchLastIndex >= users.size()) {
                    batchLastIndex = users.size();
                    result = result * batchSqlSession.insert("com.xgw.mapper.UserMapper.insertBatch",users.subList(index, batchLastIndex));
                    batchSqlSession.commit();
                    System.out.println("index:" + index+ " batchLastIndex:" + batchLastIndex);
                    break;// 数据插入完毕，退出循环
                } else {
                    result = result * batchSqlSession.insert("com.xgw.mapper.UserMapper.insertBatch",users.subList(index, batchLastIndex));
                    batchSqlSession.commit();
                    System.out.println("index:" + index+ " batchLastIndex:" + batchLastIndex);
                    index = batchLastIndex;// 设置下一批下标
                    batchLastIndex = index + (batchCount - 1);
                }
            }
            batchSqlSession.commit();
           // return batchLastIndex;
        }catch(Exception e) {
        	e.printStackTrace();
        }
        finally {
            batchSqlSession.close();
        }
        return batchLastIndex;
       // return Tools.getBoolean(result);
    }

	/*@Transactional//加入事务
	@Override
	public int  update() {
		Test test=new Test ();
		test.setId("444");
		test.setAid("777777");
		test.setTid("555");
	//	int result=testMapper.updateByPrimaryKeySelective(test);
		//int result=testMapper.insert(test);

		//int i=10/0;
		return 0;
	}*/

}
