package com.boyi.web.dao;

import com.boyi.web.entity.Person;

import java.io.Serializable;
import java.util.List;

public interface PersonDao extends HqlBaseDao<Person, Serializable> {
	
	
	/**
	 * 
	 * @param page
	 * @param pageSize
	 * @return the person by pagination
	 * @throws Exception
	 */
	public List<Person> list(Integer page, Integer pageSize) throws Exception;
}
