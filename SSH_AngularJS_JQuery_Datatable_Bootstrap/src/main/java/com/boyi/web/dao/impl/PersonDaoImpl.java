package com.boyi.web.dao.impl;

import com.boyi.web.dao.PersonDao;
import com.boyi.web.entity.Person;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository 
public class PersonDaoImpl extends HqlBaseDaoImpl<Person, Serializable> implements PersonDao {

	public PersonDaoImpl() {
		super(Person.class);
	}
	
	public List<Person> list(Integer page, Integer pageSize) throws Exception {
		return findListForPage("from Person", page, pageSize);
	}

}
