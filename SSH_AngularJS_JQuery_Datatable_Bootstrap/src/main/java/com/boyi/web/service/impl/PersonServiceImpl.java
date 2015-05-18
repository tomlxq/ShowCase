package com.boyi.web.service.impl;

import com.boyi.web.dao.PersonDao;
import com.boyi.web.entity.Person;
import com.boyi.web.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	PersonDao dao;
	
	public int count() throws Exception {
		return dao.count();
	}

	public List<Person> list(Integer page, Integer pageSize) throws Exception {
		return dao.list(page, pageSize);
	}

}
