/**
 * 
 */
package com.devhabit.departmentservice.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.devhabit.departmentservice.model.Department;

/**
 * 
 */
@Repository
public class DepartmentRepository {

	private static final Logger log = LoggerFactory.getLogger(DepartmentRepository.class);
	private List<Department> departments = new ArrayList<>();
	
	public Department add(Department department) {
		log.info("DepartmentRepository add");
		department.setId(department.getId());
		departments.add(department);
		return department;
	}
	
	public Optional<Department> findById(Long id) {
		log.info("DepartmentRepository findById");
		return departments.stream()
				.filter(a -> a.getId().equals(id))
				.findFirst();
	}
	
	public List<Department> findAll() {
		log.info("DepartmentRepository findAll");
		return departments;
	}

}
