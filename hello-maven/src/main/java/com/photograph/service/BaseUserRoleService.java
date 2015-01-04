package com.photograph.service;

import com.photograph.pojo.BaseUserRole;
import com.photograph.pojo.Criteria;

import java.util.List;

public interface BaseUserRoleService {
	int countByExample(Criteria example);

	BaseUserRole selectByPrimaryKey(String userRoleId);

	List<BaseUserRole> selectByExample(Criteria example);

}