package com.photograph.service.impl;

import com.photograph.dao.BaseRoleModuleMapper;
import com.photograph.pojo.BaseRoleModule;
import com.photograph.pojo.Criteria;
import com.photograph.service.BaseRoleModuleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaseRoleModuleServiceImpl implements BaseRoleModuleService {
	@Autowired
	private BaseRoleModuleMapper baseRoleModuleMapper;

	private static final Logger logger = LoggerFactory.getLogger(BaseRoleModuleServiceImpl.class);

	@Override
	public int countByExample(Criteria example) {
		int count = this.baseRoleModuleMapper.countByExample(example);
		logger.debug("count: {}", count);
		return count;
	}

	@Override
	public BaseRoleModule selectByPrimaryKey(String roleModuleId) {
		return this.baseRoleModuleMapper.selectByPrimaryKey(roleModuleId);
	}

	@Override
	public List<BaseRoleModule> selectByExample(Criteria example) {
		return this.baseRoleModuleMapper.selectByExample(example);
	}
}