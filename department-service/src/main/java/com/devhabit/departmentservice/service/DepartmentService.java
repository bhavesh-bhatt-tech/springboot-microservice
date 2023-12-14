/**
 * 
 */
package com.devhabit.departmentservice.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devhabit.departmentservice.model.Department;
import com.devhabit.departmentservice.repository.DepartmentRepository;
import com.devhabit.departmentservice.service.client.EmployeeClient;

/**
 * 
 */
@Service
public class DepartmentService {

	private static final Logger log = LoggerFactory.getLogger(DepartmentService.class);
	
	@Autowired
	DepartmentRepository repository;
	
	@Autowired 
	EmployeeClient employeeClient;
	
	public Department save(Department department) {
		log.info("Department save");
		return repository.add(department);	
	}

	/**
	 * @param id
	 * @return
	 */
	public Department findById(Long id) {
		log.info("Department Service findById");		
		return repository.findById(id);
	}

	/**
	 * @return 
	 */
	public List<Department> findAll() {
		log.info("Department Service findAll");
		return repository.findAll();
	}
	
	/**
	 * @return 
	 */
	public List<Department> findAllDepartmentEmployees() {
		log.info("Department Service findAllDepartmentEmployees");
		// get all departments 
		List<Department> departmentList = repository.findAll();
		for (Department department : departmentList) {
			department.setEmployeeList(employeeClient.findByDepartmentId(department.getId()));
		}
		return departmentList;
	}
}
