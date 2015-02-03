package com.tom.demo.dao;

import com.tom.demo.model.UserRole;

public interface UserRoleMapper {
    int deleteByPrimaryKey(Integer user_role_id);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(Integer user_role_id);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);
}