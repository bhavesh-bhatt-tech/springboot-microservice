package com.devhabit.departmentservice.service.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.devhabit.departmentservice.model.Department;
import com.devhabit.departmentservice.model.Employee;
import com.devhabit.departmentservice.repository.DepartmentRepository;
import com.devhabit.departmentservice.service.DepartmentService;
import com.devhabit.departmentservice.service.client.EmployeeClient;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class DepartmentServiceTests {

	private static final Logger log = LoggerFactory.getLogger(DepartmentServiceTests.class);
	
	@InjectMocks
	private DepartmentService departmentService;
	
	@Mock
	private EmployeeClient employeeClient;
	
	@Mock
	private DepartmentRepository departmentRepository;
	
	
	@Test
	void contextLoads() {
		assertThat(departmentService).isNotNull();
	}

	private List<Department> loadDepartments() {
		log.info("loadDepartments started");
		List<Department> deptList = new ArrayList<Department>();
		Department dept = new Department(1, "HR");				
		Department dept2 = new Department(2, "Account");		
		deptList.add(dept);
		deptList.add(dept2);
		departmentService.save(dept);
		departmentService.save(dept2);
		return deptList;
	}
	@Test
	void saveTest() { 

		log.info("saveTest started");
		Department dept = new Department(1, "HR");
		
		when(departmentRepository.add(dept)).thenReturn(dept);
		
		Department fetchedDept = departmentService.save(dept);
		
		assertNotNull(fetchedDept, "Object is null");
		assertEquals("HR", fetchedDept.getName());
		assertNotEquals("CS", fetchedDept.getName());

	}
	@Test
	void findByIdTest() {
		log.info("findByIdTest started");		
		loadDepartments();
		Department dept = new Department(1, "HR");
		
		when(departmentRepository.findById(dept.getId())).thenReturn(Optional.of(dept));
		
		Department d1 = departmentService.findById(1L);
		assertThat(d1).isNotNull();
		assertEquals(dept.getName(), d1.getName());
		assertEquals(dept.getId(), d1.getId());

	}

	@Test
	void findAllTest() {
		log.info("findAllTest started");		

		List<Department> departmentList = loadDepartments();
		
		when(departmentRepository.findAll()).thenReturn(departmentList);
		List<Department> deptList = departmentService.findAll();
		
		Department d1 = departmentList.get(0);
		Department d2 = departmentList.get(1);
		
		assertThat(deptList).isNotNull();		
		assertEquals(2L,deptList.size());
		
		for (Department department : deptList) {
			if(department.getId() == 1)
				assertEquals(d1.getName(),department.getName());
			else if(department.getId() == 2)
				assertEquals(d2.getName(),department.getName());
		}		
	}
	@Test
	void findAllDepartmentEmployeesTest() {
		
		log.info("findAllDepartmentEmployeesTest started");				
		// only create department 1 and 2
		List<Department> reqDeptList = loadDepartments();
		
		List<Employee> dept1EmployeeList = new ArrayList<Employee>();
		List<Employee> dept2EmployeeList = new ArrayList<Employee>();
		List<Employee> dept3EmployeeList = new ArrayList<Employee>();
		List<Employee> dept4EmployeeList = new ArrayList<Employee>();		
		HashMap map1 = new HashMap();
		HashMap map2 = new HashMap();
		map1.put("data", dept1EmployeeList);
		map2.put("data", dept2EmployeeList);

		ResponseEntity<Map<String, Object>>  response1 =  new ResponseEntity<>(map1,HttpStatus.OK);
		ResponseEntity<Map<String, Object>>  response2 =  new ResponseEntity<>(map2,HttpStatus.OK);

		createEmployees(dept1EmployeeList, dept2EmployeeList, dept3EmployeeList, dept4EmployeeList);
		// set employee list
		reqDeptList.get(0).setEmployeeList(dept1EmployeeList);
		reqDeptList.get(1).setEmployeeList(dept1EmployeeList);
		
		// mocking the employee microservice call within findAllDepartmentEmployees method of department service
		when(departmentRepository.findAll()).thenReturn(reqDeptList);
		when(employeeClient.findByDepartmentId(1L)).thenReturn(response1);			
		when(employeeClient.findByDepartmentId(2L)).thenReturn(response2);
			
		List<Department> deptList = departmentService.findAllDepartmentEmployees();
		
		assertEquals(reqDeptList.size(), deptList.size());
		
		Department d1 = deptList.get(0);
		Department d2 = deptList.get(1);
		
		assertThat(d1).isNotNull();
		assertThat(d1.getEmployeeList()).isNotNull();
		assertThat(d1.getEmployeeList().get(0)).isNotNull();

		assertThat(d2).isNotNull();
		assertThat(d2.getEmployeeList()).isNotNull();
		assertThat(d2.getEmployeeList().get(0)).isNotNull();

		if(d1.getId() == 1) {
			assertEquals(reqDeptList.get(0).getEmployeeList().get(0).getDepartmentId(), d1.getEmployeeList().get(0).getDepartmentId());	
			assertEquals(reqDeptList.get(0).getEmployeeList().get(0).getFirstName(), d1.getEmployeeList().get(0).getFirstName());				
			assertEquals(reqDeptList.get(0).getEmployeeList().get(0).getLastName(), d1.getEmployeeList().get(0).getLastName());				

		} else if(d1.getId() == 2) {
			assertEquals(reqDeptList.get(1).getEmployeeList().get(0).getDepartmentId(), d2.getEmployeeList().get(0).getDepartmentId());			
			assertEquals(reqDeptList.get(1).getEmployeeList().get(0).getFirstName(), d1.getEmployeeList().get(0).getFirstName());				
			assertEquals(reqDeptList.get(1).getEmployeeList().get(0).getLastName(), d1.getEmployeeList().get(0).getLastName());				
		}
		assertEquals(2, deptList.size());

	}

	/**
	 * @param dept1EmployeeList
	 * @param dept2EmployeeList
	 * @param dept3EmployeeList
	 * @param dept4EmployeeList
	 */
	private void createEmployees(List<Employee> dept1EmployeeList, List<Employee> dept2EmployeeList,
			List<Employee> dept3EmployeeList, List<Employee> dept4EmployeeList) {
		log.info("createEmployees started");				

		Employee e1 = new Employee(1L,"Tara","Singh");
		

		Employee e2 = new Employee(1L,"Vicky","Malhotra");
		
		Employee e3 = new Employee(2L,"Nakul","Sahani");

		Employee e4 = new Employee(3L,"Kishan","Pathak");
		
		Employee e5 = new Employee(4L,"Allen","Border");
		
		dept1EmployeeList.add(e1);
		dept1EmployeeList.add(e2);
		dept2EmployeeList.add(e3);
		dept3EmployeeList.add(e4);
		dept4EmployeeList.add(e5);
	}

}
