package com.photograph.service;

import com.photograph.pojo.BaseRoleModule;
import com.photograph.pojo.Criteria;

import java.util.List;

public interface BaseRoleModuleService {
	int countByExample(Criteria example);

	BaseRoleModule selectByPrimaryKey(String roleModuleId);

	List<BaseRoleModule> selectByExample(Criteria example);
}