/**
 * 
 */
package com.devhabit.employeeservice.repository.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.util.Assert;

import com.devhabit.employeeservice.model.Employee;
import com.devhabit.employeeservice.repository.EmployeeRepository;


/**
 * 
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class EmployeeRepositoryTest {

	private static final Logger log = LoggerFactory.getLogger(EmployeeRepositoryTest.class);
	
	@Mock
	EmployeeRepository employeeRepository;
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
	void addEmployeeTest() {
		log.info("addEmployeeTest started");
		Employee e1 = new Employee(1L, "Sumeet","Shah");
		when(employeeRepository.add(e1)).thenReturn(e1);
		Employee employee = employeeRepository.add(e1);
		assertThat(employee).isNotNull();
		assertEquals(employee.getFirstName(), e1.getFirstName());
		assertEquals(1L,employee.getId());
	}
	
	@Test
	void findByIdTest() {
		
		log.info("findByIdTest started");
		Employee e1 = new Employee(1L, "Sumeet","Shah");
		employeeRepository.add(e1);
		when(employeeRepository.findById(1L)).thenReturn(Optional.of(e1));
		Optional<Employee> employee = employeeRepository.findById(1L);
		Assert.isTrue(employee.isPresent(),"");
		assertThat(employee).isNotNull();
		assertEquals(1L,employee.get().getId());
	}
	@Test
	void findAllTest() {
		log.info("findAllTest started");
		
		List<Employee> empList = new ArrayList<>();
		Employee emp = new Employee(1L, "Sumeet","Shah");				
		Employee emp2 = new Employee(2L, "Nikunj","Mehta");		
		employeeRepository.add(emp);
		employeeRepository.add(emp2);
		empList.add(emp);
		empList.add(emp2);
		
		when(employeeRepository.findAll()).thenReturn(empList);
		List<Employee> fetchedEmpList = employeeRepository.findAll();
		assertThat(fetchedEmpList).isNotEmpty();
		assertThat(fetchedEmpList.get(0)).isNotNull();
		assertEquals(emp.getId(),fetchedEmpList.get(0).getId());
		assertEquals(emp.getFirstName(),fetchedEmpList.get(0).getFirstName());
		assertEquals(emp.getLastName(),fetchedEmpList.get(0).getLastName());
		
	}
}
