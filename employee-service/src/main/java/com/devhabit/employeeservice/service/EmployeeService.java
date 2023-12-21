/**
 * 
 */
package com.devhabit.employeeservice.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.devhabit.employeeservice.model.Employee;
import com.devhabit.employeeservice.repository.EmployeeRepository;

/**
 * 
 */
@Service
public class EmployeeService {

	private static final Logger log = LoggerFactory.getLogger(EmployeeService.class);
	
	
	private final EmployeeRepository employeeRepository;
	
	/**
	 * 
	 */
	public EmployeeService(EmployeeRepository empRepo) {
		this.employeeRepository = empRepo;
	}
	
	/**
	 * @param employee
	 * @return
	 */
	public Employee save(Employee employee) {
		log.info("Employee Service save");	
		employeeRepository.add(employee);
		return employee;
	}

	/**
	 * @param id
	 * @return
	 */
	public Employee findById(Long id) {
		log.info("Employee Service findById");	
		Optional<Employee> emp = employeeRepository.findById(id);
		if(emp.isPresent())
			return emp.get();
		else 
			return null;
	}

	/**
	 * @return
	 */
	public List<Employee> findAll() {
		log.info("Employee Service findAll");		
		return employeeRepository.findAll();
	}

	/**
	 * @return
	 */
	public List<Employee> findByDepartmentId(Long departmentId) {
		log.info("Employee Service findByDepartmentId");		
		return employeeRepository.findByDepartmentId(departmentId);
	}
	
}
