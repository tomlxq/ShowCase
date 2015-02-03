package com.tom.demo.dao;

import com.tom.demo.model.RoleModule;

public interface RoleModuleMapper {
    int deleteByPrimaryKey(Integer role_module_id);

    int insert(RoleModule record);

    int insertSelective(RoleModule record);

    RoleModule selectByPrimaryKey(Integer role_module_id);

    int updateByPrimaryKeySelective(RoleModule record);

    int updateByPrimaryKey(RoleModule record);
}