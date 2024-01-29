/**
 * 
 */
package com.devhabit.departmentservice.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.devhabit.departmentservice.model.Department;
import com.devhabit.departmentservice.model.Employee;
import com.devhabit.departmentservice.repository.DepartmentRepository;
import com.devhabit.departmentservice.service.client.EmployeeClient;

/**
 * 
 */
@Service
public class DepartmentService {

	private static final Logger log = LoggerFactory.getLogger(DepartmentService.class);

	DepartmentRepository repository;
	
	private final EmployeeClient employeeClient;
	
	/**
	 * 
	 */
	public DepartmentService(DepartmentRepository deptRepo,EmployeeClient empClient) {
		this.repository = deptRepo;
		this.employeeClient = empClient;
	}
	
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
		Optional<Department> dept = repository.findById(id);
		if(dept.isPresent()) 
			return dept.get(); 
		else return null;
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
			List<Employee> empList = null;
			Map<String,Object> map ; 
			ResponseEntity<Map<String, Object>> response = employeeClient.findByDepartmentId(department.getId());
			Optional<Map<String,Object>> body = Optional.ofNullable(response.getBody());
			if(body.isPresent()) {
				map = body.get();
				empList = (List<Employee>)map.get("data");
			}
			department.setEmployeeList(empList);
		}
		return departmentList;
	}
}
