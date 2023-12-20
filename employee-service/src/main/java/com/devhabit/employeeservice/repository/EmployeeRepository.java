/**
 * 
 */
package com.devhabit.employeeservice.repository;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.devhabit.departmentservice.model.Employee;

/**
 * 
 */
@Repository
public class EmployeeRepository {

	private static final Logger log = LoggerFactory.getLogger(EmployeeRepository.class);

	List<Employee> employeeList = new ArrayList<>();

	public Employee add(Employee employee) {
		log.info("EmployeeRepository add");
		employeeList.add(employee);
		return employee;
	}

	public Employee findById(Long id) {
		log.info("EmployeeRepository findById");
		return employeeList.stream().filter(a -> a.getId().equals(id)).findFirst().orElseThrow();
	}

	public List<Employee> findAll() {
		log.info("EmployeeRepository findAll");
		return employeeList;
	}

	/**
	 * @param departmentId
	 * @return
	 */
	public List<Employee> findByDepartmentId(Long departmentId) {
		log.info("EmployeeRepository findByDepartmentId");

		log.info("departmentId = {} ", departmentId);
		return employeeList.stream().filter(a -> a.getDepartmentId().equals(departmentId)).toList();

	}

}
