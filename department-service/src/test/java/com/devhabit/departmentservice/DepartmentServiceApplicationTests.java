package com.devhabit.departmentservice;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DepartmentServiceApplicationTests {

	@Mock
	DepartmentServiceApplication departmentServiceApplication;
	@Test
	void contextLoads() {
	}
	@Test
	void departmentServiceTest() {
		assertTrue(true);
	}
}
