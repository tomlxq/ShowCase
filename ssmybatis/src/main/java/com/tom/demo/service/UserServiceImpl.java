package com.tom.demo.service;

import com.tom.demo.dao.UserMapper;
import com.tom.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 说明：
 *
 * @author tom
 * @version 创建时间： 2015/2/3  21:30
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User getUserById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }
}
