/**
 * 
 */
package com.devhabit.departmentservice.model.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.devhabit.departmentservice.model.Department;
import com.devhabit.departmentservice.model.Employee;

/**
 * 
 */
@SpringBootTest
class DepartmentModelTest {
	
	@Mock
	Department department;

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
	void getEmployeeTest() {
		
		Employee e = new Employee(1L,"Mahesh","Oberoy");
		Department d = new Department(1L, "HR");
		List<Employee> employeeList = new ArrayList<>();
		employeeList.add(e);
		department.setEmployeeList(employeeList);
		when(department.getEmployeeList()).thenReturn(employeeList);
		List<Employee> fetchedEmpList = department.getEmployeeList();
		assertEquals(fetchedEmpList.size(), employeeList.size());
		assertThat(fetchedEmpList.get(0)).isNotNull();
		assertThat(fetchedEmpList.get(0)).isEqualTo(e);
	}

	@Test
	void getRemoveEmployeeTest() {
		
		Employee e = new Employee(1L,"Mahesh","Oberoy");
		List<Employee> employeeList = new ArrayList<>();
		employeeList.add(e);
		department.setEmployeeList(employeeList);
		employeeList.remove(0);
		when(department.getEmployeeList()).thenReturn(employeeList);
		List<Employee> fetchedEmpList = department.getEmployeeList();
		assertEquals(fetchedEmpList.size(), employeeList.size());
		assertThat(fetchedEmpList).isEmpty();
	}

}
