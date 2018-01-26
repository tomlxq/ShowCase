package com.springmvc.service.impl;

import com.springmvc.annotation.Autowired;
import com.springmvc.annotation.Service;
import com.springmvc.service.TestService;
import com.springmvc.service.TestService2;

@Service
public class TestServiceImpl implements TestService {
	
	@Autowired
	private TestService2 testService2;

	@Override
	public String test() {
		System.out.println(testService2.test2());
		return "method test do success!";
	}

}
