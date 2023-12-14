/**
 * @author bhavesh bhatt
 */
package com.devhabit.departmentservice.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 */
public class Department { 

	private Long id;
	private String name;
	private List<Employee> employeeList = new ArrayList<>();
	
	
	/**
	 * @param id
	 * @param name
	 */
	public Department(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the employeeList
	 */
	public List<Employee> getEmployeeList() {
		return employeeList;
	}
	/**
	 * @param employeeList the employeeList to set
	 */
	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}
	
	
}
