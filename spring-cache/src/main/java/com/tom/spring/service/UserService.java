package com.tom.spring.service;

import com.tom.spring.entity.User;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-1
 * <p>Version: 1.0
 */
@Service
public class UserService {

    Set<User> users = new HashSet<User>();
    /*应用到写数据的方法上，如新增/修改方法，调用方法时会自动把相应的数据放入缓存：
    * 即调用该方法时，会把user.id作为key，返回值作为value放入缓存；
    * @CachePut注解：
Java代码  收藏代码
public @interface CachePut {
    String[] value();              //缓存的名字，可以把数据写到多个缓存
    String key() default "";       //缓存key，如果不指定将使用默认的KeyGenerator生成，后边介绍
    String condition() default ""; //满足缓存条件的数据才会放入缓存，condition在调用方法之前和之后都会判断
    String unless() default "";    //用于否决缓存更新的，不像condition，该表达只在方法执行之后判断，此时可以拿到返回值result进行判断了
}  */
    @CachePut(value = "user", key = "#user.id")
    public User save(User user) {
        users.add(user);
        return user;
    }

    @CachePut(value = "user", key = "#user.id")
    public User update(User user) {
        users.remove(user);
        users.add(user);
        return user;
    }
   /* 即应用到移除数据的方法上，如删除方法，调用方法时会从缓存中移除相应的数据：
   @CacheEvict注解：
Java代码  收藏代码
public @interface CacheEvict {
    String[] value();                        //请参考@CachePut
    String key() default "";                 //请参考@CachePut
    String condition() default "";           //请参考@CachePut
    boolean allEntries() default false;      //是否移除所有数据
    boolean beforeInvocation() default false;//是调用方法之前移除/还是调用之后移除*/
    @CacheEvict(value = "user", key = "#user.id")
    public User delete(User user) {
        users.remove(user);
        return user;
    }

    @CacheEvict(value = "user", allEntries = true)//移除所有数据
    public void deleteAll() {
        users.clear();
    }
 /*   @Cacheable
    应用到读取数据的方法上，即可缓存的方法，如查找方法：先从缓存中读取，如果没有再调用方法获取数据，然后把数据添加到缓存中：
    @Cacheable注解：
Java代码  收藏代码
public @interface Cacheable {
    String[] value();             //请参考@CachePut
    String key() default "";      //请参考@CachePut
    String condition() default "";//请参考@CachePut
    String unless() default "";   //请参考@CachePut */
    @Cacheable(value = "user", key = "#id")
    public User findById(final Long id) {
        System.out.println("cache miss, invoke find by id, id:" + id);
        for (User user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }
/*
* 条件缓存
根据运行流程，如下@Cacheable将在执行方法之前( #result还拿不到返回值)判断condition，如果返回true，则查缓存；
Java代码  收藏代码
@Cacheable(value = "user", key = "#id", condition = "#id lt 10")
public User conditionFindById(final Long id)

根据运行流程，如下@CachePut将在执行完方法后（#result就能拿到返回值了）判断condition，如果返回true，则放入缓存；
Java代码  收藏代码
@CachePut(value = "user", key = "#id", condition = "#result.username ne 'zhang'")
public User conditionSave(final User user)

根据运行流程，如下@CachePut将在执行完方法后（#result就能拿到返回值了）判断unless，如果返回false，则放入缓存；（即跟condition相反）
Java代码  收藏代码
@CachePut(value = "user", key = "#user.id", unless = "#result.username eq 'zhang'")
    public User conditionSave2(final User user)

根据运行流程，如下@CacheEvict， beforeInvocation=false表示在方法执行之后调用（#result能拿到返回值了）；且判断condition，如果返回true，则移除缓存；
Java代码  收藏代码
@CacheEvict(value = "user", key = "#user.id", beforeInvocation = false, condition = "#result.username ne 'zhang'")
public User conditionDelete(final User user)   */
}
