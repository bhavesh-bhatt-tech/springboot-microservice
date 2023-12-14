/**
 * 
 */
package com.devhabit.departmentservice.controller;

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

import com.devhabit.departmentservice.model.Department;
import com.devhabit.departmentservice.repository.DepartmentRepository;
import com.devhabit.departmentservice.service.DepartmentService;
import com.devhabit.departmentservice.service.client.EmployeeClient;

/**
 * 
 */

@RestController
@RequestMapping("/department")
public class DepartmentController {

	private static final Logger log = LoggerFactory.getLogger(DepartmentController.class);
	
	@Autowired
	DepartmentRepository departmentRepository;
	@Autowired
	DepartmentService departmentService;
		
	@PostMapping("/add")
	public Department add(@RequestBody Department department) {
		log.info("Department add: {}", department);
		return departmentService.save(department);
	}
	@GetMapping("/{id}")
	public Department findById(@PathVariable("id") Long id) {
		log.info("Department find: id={}", id);
		return departmentService.findById(id);
	}
	
	@GetMapping("/all")
	public List<Department> findAll() {
		log.info("Department findAll ");
		return departmentService.findAll();
	}
	@GetMapping("/all/department-employee")
	public List<Department> findAllDepartmentEmployees() {
		log.info("Department findAll Department and Employees");
		return departmentService.findAllDepartmentEmployees();
	}

}
