package com.tom.mvc.service;

import com.tom.mvc.entity.User;

/**
 * 说明：
 *
 * @author tom
 * @version 创建时间： 2015/2/10  20:44
 */
public interface UserService {

    public User findById(Long id);
}
