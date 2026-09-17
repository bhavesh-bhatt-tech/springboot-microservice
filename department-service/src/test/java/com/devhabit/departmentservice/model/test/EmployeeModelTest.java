/**
 * 
 */
package com.devhabit.departmentservice.model.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.devhabit.departmentservice.model.Employee;

/**
 * 
 */
class EmployeeModelTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
        //no setUp processing is required
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {

        //no tearDown processing is required
	}

	@Test
	void twoEqualEmployeeTest() {
		
		Employee e1 = new Employee(1L,"Campbell","Arister");
		Employee e2 = new Employee(2L,"Roger","Sabestian");
		Employee e3 = new Employee(3L,"Roger","Sabestian");
		e2.setDateOfBirth("25-jul-1991");
		e3.setDateOfBirth("25-jul-1991");
		assertNotEquals(e1, e2);;
		assertEquals(e2, e3);

	}

}
