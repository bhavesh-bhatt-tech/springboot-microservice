/**
 * 
 */
package com.devhabit.employeeservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.devhabit.departmentservice.model.Employee;
import com.devhabit.employeeservice.repository.EmployeeRepository;
import com.devhabit.employeeservice.service.EmployeeService;

/**
 * 
 */

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);
	
	@Autowired
	EmployeeRepository EmployeeRepository;
	
	@Autowired
	EmployeeService employeeService;
	
	@PostMapping("/add")
	public Employee add(@RequestBody Employee Employee) {
		log.info("Employee add: {}", Employee);
		return employeeService.save(Employee);
	}
	@GetMapping("/{id}")
	public Employee findById(@PathVariable("id") Long id) {
		log.info("Employee find: id={}", id);
		return employeeService.findById(id);
	}
	
	@GetMapping("/all")
	public List<Employee> findAll() {
		log.info("Employee findAll ");
		return employeeService.findAll();
	}
	@GetMapping("/department/{departmentId}")
	public List<Employee> findByDepartmentId(@PathVariable("departmentId") Long departmentId) {
		log.info("Employee findByDepartmentId  {}", departmentId);
		return employeeService.findByDepartmentId(departmentId);
	}
}
