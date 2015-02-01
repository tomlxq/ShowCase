package com.tom.demo.service;

import com.tom.demo.entity.User;

import java.util.List;

/**
 * 说明：
 *
 * @author tom
 * @version 创建时间： 2015/1/31  21:13
 */
public interface UserService {
    User saveUser(User user);
    List<User> queryUserAll();
}
