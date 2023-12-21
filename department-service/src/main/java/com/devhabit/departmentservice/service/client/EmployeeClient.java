/**
 * 
 */
package com.devhabit.departmentservice.service.client;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

/**
 * Declarative client
 */
@HttpExchange
public interface EmployeeClient {
	@GetExchange("/employee/department/{departmentId}")
	public ResponseEntity<Map<String, Object>> findByDepartmentId(@PathVariable("departmentId") Long departmentId);
}
