package com.boyi.web.service;

import com.boyi.web.entity.Person;

import java.util.List;

public interface PersonService {
	/**
	 * return the person counts
	 * @return
	 * @throws Exception
	 */
	public int count() throws Exception;
	
	/**
	 * 
	 * @param page
	 * @param pageSize
	 * @return the person by pagination
	 * @throws Exception
	 */
	public List<Person> list(Integer page, Integer pageSize) throws Exception;
}
