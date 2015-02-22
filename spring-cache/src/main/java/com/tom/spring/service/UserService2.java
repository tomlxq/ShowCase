package com.tom.spring.service;

import com.tom.spring.entity.User;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-1
 * <p>Version: 1.0
 */
@Service
public class UserService2 {

    Set<User> users = new HashSet<User>();
    /*有时候我们可能组合多个Cache注解使用；比如用户新增成功后，我们要添加id-->user；username--->user；email--->user的缓存；此时就需要@Caching组合多个注解标签了。

    如用户新增成功后，添加id-->user；username--->user；email--->user到缓存；*/
    @Caching(
            put = {
                    @CachePut(value = "user", key = "#user.id"),
                    @CachePut(value = "user", key = "#user.username"),
                    @CachePut(value = "user", key = "#user.email")
            }
    )
    public User save(User user) {
        users.add(user);
        return user;
    }

    /**
     * 自定义缓存注解
     比如之前的那个@Caching组合，会让方法上的注解显得整个代码比较乱，此时可以使用自定义注解把这些注解组合到一个注解中，如：
     Java代码  收藏代码
     @Caching(
     put = {
     @CachePut(value = "user", key = "#user.id"),
     @CachePut(value = "user", key = "#user.username"),
     @CachePut(value = "user", key = "#user.email")
     }
     )
     @Target({ElementType.METHOD, ElementType.TYPE})
     @Retention(RetentionPolicy.RUNTIME)
     @Inherited
     public @interface UserSaveCache {
     }
     这样我们在方法上使用如下代码即可，整个代码显得比较干净
     */
    @UserSaveCache
    public User save2(User user){
        users.add(user);
        return user;
    }
    //新增/修改数据时往缓存中写
    @Caching(
            put = {
                    @CachePut(value = "user", key = "#user.id"),
                    @CachePut(value = "user", key = "#user.username"),
                    @CachePut(value = "user", key = "#user.email")
            }
    )
    public User update(User user) {
        users.remove(user);
        users.add(user);
        return user;
    }
   //删除数据时从缓存中移除
    @Caching(
            evict = {
                    @CacheEvict(value = "user", key = "#user.id"),
                    @CacheEvict(value = "user", key = "#user.username"),
                    @CacheEvict(value = "user", key = "#user.email")
            }
    )
    public User delete(User user) {
        users.remove(user);
        return user;
    }

    @CacheEvict(value = "user", allEntries = true)
    public void deleteAll() {
        users.clear();
    }
    //查找时从缓存中读
    //比如findByUsername时，不应该只放username-->user，应该连同id--->user和email--->user一起放入；这样下次如果按照id查找直接从缓存中就命中了；这需要根据之前的运行流程改造CacheAspectSupport：
    @Caching(
            cacheable = {
                    @Cacheable(value = "user", key = "#id")
            },
            put = {
                    @CachePut(value = "user", key = "#result.username", condition = "#result != null"),
                    @CachePut(value = "user", key = "#result.email", condition = "#result != null")
            }
    )
    public User findById(final Long id) {
        System.out.println("cache miss, invoke find by id, id:" + id);
        for (User user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }


    @Caching(
            cacheable = {
                    @Cacheable(value = "user", key = "#username")
            },
            put = {
                    @CachePut(value = "user", key = "#result.id", condition = "#result != null"),
                    @CachePut(value = "user", key = "#result.email", condition = "#result != null")
            }
    )
    public User findByUsername(final String username) {
        System.out.println("cache miss, invoke find by username, username:" + username);
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    @Caching(
            cacheable = {
                    @Cacheable(value = "user", key = "#email")
            },
            put = {
                    @CachePut(value = "user", key = "#result.id", condition = "#result != null"),
                    @CachePut(value = "user", key = "#result.email", condition = "#result != null")
            }
    )
    public User findByEmail(final String email) {
        System.out.println("cache miss, invoke find by email, email:" + email);
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }


    @Caching(
            evict = {
//                    @CacheEvict(value = "user", key = "#user.id", condition = "#root.target.canCache() and #root.caches[0].get(#user.id).get().username ne #user.username", beforeInvocation = true)
                    @CacheEvict(value = "user", key = "#user.id", condition = "#root.target.canEvict(#root.caches[0], #user.id, #user.username)", beforeInvocation = true)
            }
    )
    public void conditionUpdate(User user) {
        users.remove(user);
        users.add(user);
    }


    public boolean canEvict(Cache userCache, Long id, String username) {
        User cacheUser = userCache.get(id, User.class);
        if (cacheUser == null) {
            return false;
        }
        return !cacheUser.getUsername().equals(username);
    }

}
