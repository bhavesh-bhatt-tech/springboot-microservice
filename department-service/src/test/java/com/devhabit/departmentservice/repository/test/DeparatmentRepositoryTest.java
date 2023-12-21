/**
 * 
 */
package com.devhabit.departmentservice.repository.test;

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

import com.devhabit.departmentservice.model.Department;
import com.devhabit.departmentservice.repository.DepartmentRepository;

/**
 * 
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class DeparatmentRepositoryTest {

	private static final Logger log = LoggerFactory.getLogger(DeparatmentRepositoryTest.class);

	@Mock
	DepartmentRepository departmentRepository;
	
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		log.info("called before each method call");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		log.info("called after each method call");
	}
	@Test
	void addDepartmentTest() {
		log.info("addDepartmentTest started");
		Department d1 = new Department(1, "HR");
		when(departmentRepository.add(d1)).thenReturn(d1);
		Department fetchedDept = departmentRepository.add(d1);

		assertEquals(1L,fetchedDept.getId());
	
	}
	@Test
	void findByIdTest() {
		log.info("findByIdTest started");
		Department d1 = new Department(1, "HR");
		departmentRepository.add(d1);
		when(departmentRepository.findById(1L)).thenReturn(Optional.of(d1));
		Optional<Department> department = departmentRepository.findById(1L);
		Assert.isTrue(department.isPresent(),"");
		assertThat(department).isNotNull();
		assertEquals(1L,department.get().getId());
		
		
	}
	@Test
	void findAllTest() {
		log.info("findAll started");
		
		List<Department> departmentList = new ArrayList<>();
		Department dept = new Department(1, "HR");				
		Department dept2 = new Department(2, "Account");		
		departmentRepository.add(dept);
		departmentRepository.add(dept2);
		departmentList.add(dept);
		departmentList.add(dept2);
		
		
		when(departmentRepository.findAll()).thenReturn(departmentList);
		List<Department> deptList = departmentRepository.findAll();
		assertThat(deptList).isNotEmpty();
		assertThat(deptList.get(0)).isNotNull();
		assertEquals(dept.getName(),deptList.get(0).getName());
		
	}
}
