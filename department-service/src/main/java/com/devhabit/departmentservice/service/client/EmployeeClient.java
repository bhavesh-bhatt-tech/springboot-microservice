/**
 * 
 */
package com.devhabit.departmentservice.service.client;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import com.devhabit.departmentservice.model.Employee;

/**
 * Declarative client
 */
@HttpExchange
public interface EmployeeClient {
	@GetExchange("/employee/department/{departmentId}")
	public List<Employee> findByDepartmentId(@PathVariable("departmentId") Long departmentId);
}
