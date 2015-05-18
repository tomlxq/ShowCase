package com.boyi.web.action;

import com.boyi.web.entity.Person;
import com.boyi.web.service.PersonService;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

@ParentPackage("json-default")
@Controller
public class PersonAjaxAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	/**
	 * datatable start value
	 */
	private int start;

	/**
	 * datatable page size
	 */
	private int length;
	private int recordsTotal;
	private int recordsFiltered;
	private int recordsDisplay;
	private List<Person> aaData;
	@Resource
	PersonService service;

	@Override
	@Action(value = "/persons", results = { @Result(name = "success", type = "json") })
	public String execute() throws Exception {
		// calculate the total records
		recordsTotal = service.count();

		// calculate the current page
		int page = start / length + 1;

		// query the persons
		aaData = service.list(page, length);
		
		recordsFiltered = recordsTotal;
		
		recordsDisplay = aaData.size();
		
		return ActionSupport.SUCCESS;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public int getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(int recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	public List<Person> getAaData() {
		return aaData;
	}

	public void setAaData(List<Person> aaData) {
		this.aaData = aaData;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getRecordsDisplay() {
		return recordsDisplay;
	}

	public void setRecordsDisplay(int recordsDisplay) {
		this.recordsDisplay = recordsDisplay;
	}
	
}
