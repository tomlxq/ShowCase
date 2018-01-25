package com.example.demo.Spring事务处理机制;

import java.util.List;

/**
 * Created by tom on 2018/1/25.
 */


public interface IUserDAO {

    public void addUser(User user);

    public void deleteUser(int id);

    public void updateUser(User user);

    public String searchUserName(int id);

    public User searchUser(int id);

    public List<User> findAll();

}