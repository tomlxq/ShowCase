package com.tom.mvc.service;

import com.tom.mvc.entity.User;
import org.springframework.stereotype.Service;

/**
 * 说明：
 *
 * @author tom
 * @version 创建时间： 2015/2/10  20:45
 */
@Service
public class UserServiceImpl implements UserService {

    public User findById(Long id) {
        User user = new User();
        user.setId(id);
        user.setName("tom");
        return user;
    }
}