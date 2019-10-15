package com.xgw.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    
    //redis:string类型set
    public void setString(String key,String value){
        stringRedisTemplate.opsForValue().set(key,value);
    }
    
    //redis:string类型get
    public String getString(String key){
        return stringRedisTemplate.opsForValue().get(key);
    }
    
    //redis:hash类型set
    public void setHash(String key,String filedKey,String value){
        stringRedisTemplate.opsForHash().put(key,filedKey,value);
    }
    
    //redis:hash类型的set整个map
    public void setHashAll(String key,Map<Object,Object> value) {
       stringRedisTemplate.opsForHash().putAll(key, value);
    }
    
    /**
     * redis:hash类型的set，如果key存在则不覆盖
     * @param key
     * @param filedKey
     * @param value
     */
    public void setHashAbsent(String key,String filedKey,String value) {
       stringRedisTemplate.opsForHash().putIfAbsent(key, filedKey, value);
    }
    
    //redis:hash类型get
    public String getHash(String key,String filedkey){
        String keyvalue = (String) stringRedisTemplate.opsForHash().get(key,filedkey);
        return keyvalue;
    }
    
    /**
     * reids:hash类型的get，获取整个map
     * @param key
     * @return
     */
    public Map<Object, Object> getHashAll(String key) {
       Map<Object, Object> a=stringRedisTemplate.opsForHash().entries(key);
       return a;
    }
    
    
    /**
     * reids:list类型的set，leftPush中，pivot参数是list中的value值，如果pivot=bb，即往bb元素前面插入aa
     * rightPush是往后插入元素，注意：插入成功则返回list的long类型的元素个数，如果不成功则返回-1
     * @param key
     * @param pivot
     * @param value
     * @return
     */
    public long setList(String key,String pivot,String value){
        long issetok = stringRedisTemplate.opsForList().rightPush(key, pivot, value);
        return issetok;
    }
    
    
    /**
     * redis：list类型的set，set一个list，
     * @param key
     * @param values
     * @return
     */
    public long setListAll(String key,List<String> values) {
       long issetok=stringRedisTemplate.opsForList().rightPushAll(key, values);
       return issetok;
    }
    
    
    /**
     * redis:list类型get，start=0,end=-1,获取list的全部值
     * @param key
     * @param start
     * @param end
     * @return
     */
    public List<String> getList(String key, long start, long end){
        List<String> valList = stringRedisTemplate.opsForList().range(key, start, end);
        return valList;
    }
    
    
    /**
     * redis：Set类型set，连续添加多个值，返回添加的个数
     * @param key
     * @param values
     * @return
     */
    public long setSet(String key,String... values) {
       long a=stringRedisTemplate.opsForSet().add(key, values);
       return a;
    }
    
    /**
     * 
     * redis：Set类set，key集合中不在otherKeys的数据中，新建一个destKey的Set，并存放这些key中的非交集数据
     * 注意：Collection otherKeys为多个Set的keyName,也可以是一个，即String otherKeys方法
     * 注意：返回的long数据是key Set的不同数据条数
     * @param key
     * @param otherKeys
     * @param destKey
     * @return
     */
    public long setSet_differenceAndStore(String key,Collection<String> otherKeys,String destKey) {
       long a=stringRedisTemplate.opsForSet().differenceAndStore(key, otherKeys, destKey);
       return a;
    }
    
    
    /**
     * redis：Set类的get，比较两个Set的不同，并返回一个不同结果集Set(key Set中不同于otherKey Set的数据)
     * 注意：返回的Set是key Set中不同于otherKey Set的数据，并不是返回两者全部不同数据
     * @param key
     * @param otherKey
     * @return
     */
    public Set<String> getSet_difference(String key,String otherKey){
       return stringRedisTemplate.opsForSet().difference(key, otherKey);
    }
    
    
    /**
     * redis：Set类的get，比较两个Set的相同元素，并返回相同元素的结果集
     * 提示：intersectAndStore和differenceAndStore用法类似
     * @param key
     * @param otherKey
     * @return
     */
    public Set<String> getSet_intersect(String key,String otherKey){
       return stringRedisTemplate.opsForSet().intersect(key, otherKey);
    }
    
    /**
     * redis：Set类的get，获取key Set的全部结果集
     * @param key
     * @return
     */
    public Set<String> getSet_all(String key){
       return stringRedisTemplate.opsForSet().members(key);
    }
    
    
    /**
     * redis：Set类的get，两个Set的union，返回union之后的结果集
     * 注意：得到的Set已去重！！！！
     * 提示：unionAndStore和intersectAndStore这些的用法类似
     * @param key
     * @param otherKey
     * @return
     */
    public Set<String> getSet_union(String  key,String otherKey){
       return stringRedisTemplate.opsForSet().union(key, otherKey);
    }
    
    
    /**
     * redis：Set的pop，无序集合，随机删除key Set的元素。
     * @param key
     */
    public String popSet(String key){
       return stringRedisTemplate.opsForSet().pop(key);
    }
    
    /**
     * redis：Set的remove，删除key Set中指定的value，删除成功返回删除了的条数，删除失败则返回0
     * @param key
     * @param values
     * @return
     */
    public long removeSet(String key,Object... values) {
       long a=stringRedisTemplate.opsForSet().remove(key, values);
       return a;
    }
    
    
    /**
     * @param key
     * redis:list类型delete
     * rightPop:从右边逐个删除，返回删除的value，当全部删除完的时候返回null，同时这个list也将不存在
     */
    public String deleteList(String key){
        String issetok = stringRedisTemplate.opsForList().rightPop(key);
        return issetok;
    }
    
    //设置key的失效时间
    public boolean setTimeOut(String key, long outtime){
        boolean isKeyOut = stringRedisTemplate.expire(key,outtime,TimeUnit.MINUTES);
        return isKeyOut;
    }



}
