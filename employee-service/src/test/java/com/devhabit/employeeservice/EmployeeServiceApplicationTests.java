package com.devhabit.employeeservice;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EmployeeServiceApplicationTests {
	
	@Mock
	EmployeeServiceApplication employeeServiceApplication;
	
	@Test
	void empoloyeeServiceTest() {
		assertTrue(true);
	}
}
