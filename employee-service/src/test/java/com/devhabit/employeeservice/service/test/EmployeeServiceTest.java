/**
 * 
 */
package com.devhabit.employeeservice.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.devhabit.employeeservice.model.Employee;
import com.devhabit.employeeservice.repository.EmployeeRepository;
import com.devhabit.employeeservice.service.EmployeeService;

/**
 * 
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class EmployeeServiceTest {
	
	
	private static final Logger log = LoggerFactory.getLogger(EmployeeServiceTest.class);
	@InjectMocks
	private EmployeeService employeeService;
		
	@Mock
	private EmployeeRepository employeeRepository;


	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}
	@Test
	void saveTest() {
		log.info("saveTest started");
		
		Employee emp = new Employee(1L,"Niraj","Sharma");
		
		when(employeeRepository.add(emp)).thenReturn(emp);
		
		Employee fetchedEmp = employeeService.save(emp);
		
		assertNotNull(fetchedEmp, "Object is null");
		assertEquals("Niraj", fetchedEmp.getFirstName());
		assertEquals("Sharma", fetchedEmp.getLastName());

	}
	@Test
	void findByIdTest() {
		log.info("saveTest started");
		Employee emp = new Employee(1L,"Niraj","Sharma");
		employeeService.save(emp);
		
		when(employeeRepository.findById(1L)).thenReturn(Optional.of(emp));
		
		Employee fetchedEmp = employeeService.findById(1L);
		
		assertNotNull(fetchedEmp, "Object is null");
		assertEquals("Niraj", fetchedEmp.getFirstName());
		assertEquals("Sharma", fetchedEmp.getLastName());
		
	}
	@Test
	void findByDepartmentIdTest() {
		log.info("saveTest started");
		List<Employee> empList = new ArrayList<>();
		Employee e1 = new Employee(1L,"Niraj","Sharma");
		e1.setDepartmentId(1L);
		
		Employee e2 = new Employee(2L,"Keyur","Mishra");
		e2.setDepartmentId(1L);

		Employee e3 = new Employee(3L,"Vivek","Upadhyay");
		e3.setDepartmentId(3L);

		empList.add(e1);
		empList.add(e2);
		
		when(employeeRepository.findByDepartmentId(1L)).thenReturn(empList);
		List<Employee> fetchedEmpList = employeeService.findByDepartmentId(1L);
		assertNotNull(fetchedEmpList);
		assertEquals(2,fetchedEmpList.size());
		assertEquals("Niraj",fetchedEmpList.get(0).getFirstName());
		
	}
}
