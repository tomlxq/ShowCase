package com.tom.demo.service;

import com.tom.demo.dao.UserRepository;
import com.tom.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 说明：
 *
 * @author tom
 * @version 创建时间： 2015/1/31  20:34
 */
@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    @Transactional
    public User saveUser(User user) {
       return userRepository.save(user);
    }

    @Override
    public List<User> queryUserAll() {
        return userRepository.findAll();
    }


}
