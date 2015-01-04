package com.photograph.service;

import com.photograph.pojo.Criteria;
import com.photograph.pojo.Tree;
import com.photograph.service.BaseModuleService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertNotNull;

/**
 * 
 * 
 * @author chenxin
 * @date 2011-12-7 下午2:27:04
 */
public class BaseModulesServiceTest extends ServicesTest {
	private static final Logger logger = LoggerFactory.getLogger(BaseModulesServiceTest.class);
	@Autowired
	private BaseModuleService baseModuleService;

	@Test
	public void selectAllModules() {
		Criteria criteria = new Criteria();
		Tree tree = baseModuleService.selectAllModules(criteria);
		logger.debug("{}",tree);
		assertNotNull(tree);
	}
}
