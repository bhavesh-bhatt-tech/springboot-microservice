/**
 * 
 */
package com.devhabit.departmentservice.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.devhabit.departmentservice.model.Department;
import com.devhabit.departmentservice.service.DepartmentService;

/**
 * 
 */

@RestController
@RequestMapping("/department")
public class DepartmentController {

	private static final Logger log = LoggerFactory.getLogger(DepartmentController.class);
	private static String MESSAGE = "message";
		
	private final DepartmentService departmentService;
	
	/**
	 * 
	 */
	public DepartmentController(DepartmentService deptService) {
		this.departmentService = deptService;
	}
	
	@PostMapping("/add")
	public ResponseEntity<Map<String, Object>> add(@RequestBody Department department) {
		Map<String, Object> map = new LinkedHashMap<>();

		log.info("Department add: {}", department);
		Department dept = departmentService.save(department);
		map.put("data", dept);
		map.put(MESSAGE, "Department is saved successfully");
		
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
	@GetMapping("/{id}")
	public ResponseEntity<Map<String, Object>> findById(@PathVariable("id") Long id) {
		Map<String, Object> map = new LinkedHashMap<>();
		log.info("Department find: id={}", id);
		Department department = departmentService.findById(id);
		
		map.put("data", department);
		map.put(MESSAGE, "Department retrieved successfully");
		
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<Map<String, Object>> findAll() {
		Map<String, Object> map = new LinkedHashMap<>();
		
		log.info("Department findAll ");
		List<Department> deptList = departmentService.findAll();
		map.put("data", deptList);
		map.put(MESSAGE, "Departments retrieved successfully");

		return new ResponseEntity<>(map,HttpStatus.OK);
	}
	@GetMapping("/all/department-employee")
	public ResponseEntity<Map<String, Object>> findAllDepartmentEmployees() {
		Map<String, Object> map = new LinkedHashMap<>();
		
		log.info("Department findAll Department and Employees");
		List<Department> deptList = departmentService.findAllDepartmentEmployees();
		map.put("data", deptList);
		map.put(MESSAGE, "Departments with employees retrieved successfully");
		
		return new ResponseEntity<>(map,HttpStatus.OK);
	}

}
