/**
 * 
 */
package com.devhabit.employeeservice.controller;

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

import com.devhabit.employeeservice.model.Employee;
import com.devhabit.employeeservice.service.EmployeeService;

/**
 * 
 */

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);
		
	private final EmployeeService employeeService;
	private static String MESSAGE = "message";
	
	/**
	 * @param employeeService
	 */
	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}
	
	@PostMapping("/add")
	public ResponseEntity<Map<String, Object>> add(@RequestBody Employee Employee) {
		Map<String, Object> map = new LinkedHashMap<>();
		log.info("Employee add: {}", Employee);
		Employee emp = employeeService.save(Employee);
		
		map.put("data", emp);
		map.put(MESSAGE, "Department is saved successfully");
		
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
	@GetMapping("/{id}")
	public  ResponseEntity<Map<String, Object>> findById(@PathVariable("id") Long id) {
		Map<String, Object> map = new LinkedHashMap<>();
		log.info("Employee find: id={}", id);
		Employee employee = employeeService.findById(id);
		
		map.put("data", employee);
		map.put(MESSAGE, "Department retrieved successfully");
		
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<Map<String, Object>> findAll() {
		Map<String, Object> map = new LinkedHashMap<>();
		log.info("Employee findAll ");
		List<Employee> empList = employeeService.findAll();
		
		map.put("data", empList);
		map.put(MESSAGE, "Department retrieved successfully");
		
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
	@GetMapping("/department/{departmentId}")
	public ResponseEntity<Map<String, Object>> findByDepartmentId(@PathVariable("departmentId") Long departmentId) {
		Map<String, Object> map = new LinkedHashMap<>();
		
		log.info("Employee findByDepartmentId  {}", departmentId);
		List<Employee> empList = employeeService.findByDepartmentId(departmentId);
		map.put("data", empList);
		map.put(MESSAGE, "Employees for a department retrieved successfully");
		
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
}
