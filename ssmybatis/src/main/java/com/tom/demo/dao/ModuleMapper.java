package com.tom.demo.dao;

import com.tom.demo.model.Module;

public interface ModuleMapper {
    int deleteByPrimaryKey(Integer module_id);

    int insert(Module record);

    int insertSelective(Module record);

    Module selectByPrimaryKey(Integer module_id);

    int updateByPrimaryKeySelective(Module record);

    int updateByPrimaryKey(Module record);
}