package com.springmvc.service.impl;

import com.springmvc.annotation.Service;
import com.springmvc.service.TestService2;

@Service
public class TestServiceImpl2 implements TestService2 {

	@Override
	public String test2() {
		return "method test2 do success!";
	}

}
