package com.tom.spring.service;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Caching;

import java.lang.annotation.*;

/**
 * 说明：
 *
 * @author tom
 * @version 创建时间： 2015/2/16  17:36
 */
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
