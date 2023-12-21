/**
 * 
 */
package com.devhabit.departmentservice.controller.test;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.devhabit.departmentservice.controller.DepartmentController;
import com.devhabit.departmentservice.model.Department;
import com.devhabit.departmentservice.model.Employee;
import com.devhabit.departmentservice.repository.DepartmentRepository;
import com.devhabit.departmentservice.service.DepartmentService;
import com.devhabit.departmentservice.service.client.EmployeeClient;

/**
 * 
 */
@SpringBootTest
@AutoConfigureMockMvc
class DepartmentControllerTest {

	private static final Logger log = LoggerFactory.getLogger(DepartmentControllerTest.class);
	@Autowired
	MockMvc mockMvc;
	
	@Mock
	private DepartmentController departmentController;

	@Mock
	private EmployeeClient employeeClient;
	
	
	@Mock
	private DepartmentService departmentService;
	
	@Mock
	private DepartmentRepository departmentRepository;
	
	List<Department> departmentList = new ArrayList<>();

	@Test
	void findByDepartmentIdTest() throws Exception {
		log.info("findByDepartmentIdTest started");
		Department department = new Department(1, "HR");
		//Optional<Object> dept = Optional.of((Object) department);
		departmentRepository.add(department);
		Mockito.when(departmentService.findById(1L)).thenReturn((department));
		Mockito.when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));
		
		mockMvc.perform(MockMvcRequestBuilders.get("/department/{id}", 1L)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("data").value(IsNull.nullValue()));
	}

	@Test
	void addDepartmentTest() throws Exception {
		log.info("addDepartmentTest started");

		Department department = new Department(1, "HR");				
		Mockito.when(departmentService.save(department)).thenReturn(department);
		Mockito.when(departmentRepository.add(department)).thenReturn(department);
		mockMvc.perform(MockMvcRequestBuilders.post("/department/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\r\n"
						+ "    \"id\" : \"1\",\r\n"
						+ "    \"name\" : \"HR\"\r\n"
						+ "}")).andExpect(MockMvcResultMatchers.status().isOk())
		
				.andExpect(MockMvcResultMatchers.jsonPath("data","{\r\n"
								+ "        \"id\": 1,\r\n"
								+ "        \"name\": \"HR\",\r\n"
								+ "        \"employeeList\": []\r\n"
								+ "    }").exists())
				
				.andExpect(MockMvcResultMatchers.jsonPath("message","Department is saved successfully").exists());

	}
	@Test
	void findAllTest() throws Exception {
		log.info("findAllTest started");
		Department department = new Department(1, "HR");		
		Department department2 = new Department(1, "Account");
		departmentList.add(department);
		departmentList.add(department2);
		
		Mockito.when(departmentService.findAll()).thenReturn(departmentList);
		Mockito.when(departmentRepository.findAll()).thenReturn(departmentList);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/department/all")
				.contentType(MediaType.APPLICATION_JSON)
				).andExpect(MockMvcResultMatchers.status().isOk())
		
		.andExpect(MockMvcResultMatchers.jsonPath("data","[\r\n"
				+ "        {\r\n"
				+ "            \"id\": 1,\r\n"
				+ "            \"name\": \"HR\",\r\n"
				+ "            \"employeeList\": []\r\n"
				+ "        },\r\n"
				+ "        {\r\n"
				+ "            \"id\": 2,\r\n"
				+ "            \"name\": \"Account\",\r\n"
				+ "            \"employeeList\": []\r\n"
				+ "        }\r\n"
				+ "    ]").exists())
		
		.andExpect(MockMvcResultMatchers.jsonPath("message","Departments retrieved successfully").exists());
	}
	
	@Test
	void findAllDepartmentEmployeesTest() throws Exception {
		log.info("findAllDepartmentEmployeesTest started");
		
		// only create department 1 and 2
		List<Department> reqDeptList = loadDepartments();

		Employee employee = new Employee(1L, "Sumeet","Shah");		
		Employee employee2 = new Employee(1L, "Chirag","Maru");
		Employee employee3 = new Employee(2L, "Vivek","Taneja");
		
		employee.setDateOfBirth("05-Nov-1984");
		employee2.setDateOfBirth("15-Sep-1986");
		employee3.setDateOfBirth("07-jul-1983");
		employee.setDepartmentId(1L);
		employee2.setDepartmentId(1L);
		employee3.setDepartmentId(2L);
		
		List<Employee> dept1EmployeeList = new ArrayList<Employee>();
		List<Employee> dept2EmployeeList = new ArrayList<Employee>();
		dept1EmployeeList.add(employee);
		dept1EmployeeList.add(employee2);
		dept2EmployeeList.add(employee3);
		
		// set employee list
		reqDeptList.get(0).setEmployeeList(dept1EmployeeList);
		reqDeptList.get(1).setEmployeeList(dept2EmployeeList);
		HashMap <String,Object>map1 = new HashMap<>();	
		HashMap <String,Object>map2 = new HashMap<>();	
		
		map1.put("data", dept1EmployeeList);
		map2.put("data", dept2EmployeeList);
		Mockito.when(departmentService.findAllDepartmentEmployees()).thenReturn(reqDeptList);
		ResponseEntity<Map<String, Object>> response1 = new ResponseEntity(map1,HttpStatus.OK);
		ResponseEntity<Map<String, Object>> response2 = new ResponseEntity(map2,HttpStatus.OK);
		
		Mockito.when(departmentRepository.findAll()).thenReturn(reqDeptList);
		when(employeeClient.findByDepartmentId(1L)).thenReturn(response1);			
		when(employeeClient.findByDepartmentId(2L)).thenReturn(response2);
		
		
		mockMvc.perform(MockMvcRequestBuilders.get("/department/all/department-employee")
				.contentType(MediaType.APPLICATION_JSON)
				).andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("data","{\r\n"
				+ "            \"id\": 1,\r\n"
				+ "            \"name\": \"HR\",\r\n"
				+ "            \"employeeList\": [\r\n"
				+ "                {\r\n"
				+ "                    \"id\": 1,\r\n"
				+ "                    \"departmentId\": 1,\r\n"
				+ "                    \"firstName\": \"Sumeet\",\r\n"
				+ "                    \"lastName\": \"Shah\",\r\n"
				+ "                    \"dateOfBirth\": \"05-Nov-1984\"\r\n"
				+ "                },\r\n"
				+ "                {\r\n"
				+ "                    \"id\": 1,\r\n"
				+ "                    \"departmentId\": 1,\r\n"
				+ "                    \"firstName\": \"Chirag\",\r\n"
				+ "                    \"lastName\": \"Maru\",\r\n"
				+ "                    \"dateOfBirth\": \"15-Sep-1986\"\r\n"
				+ "                }\r\n"
				+ "            ]\r\n"
				+ "        },\r\n"
				+ "        {\r\n"
				+ "            \"id\": 2,\r\n"
				+ "            \"name\": \"Account\",\r\n"
				+ "            \"employeeList\": [\r\n"
				+ "                {\r\n"
				+ "                    \"id\": 3,\r\n"
				+ "                    \"departmentId\": 2,\r\n"
				+ "                    \"firstName\": \"Vivek\",\r\n"
				+ "                    \"lastName\": \"Taneja\",\r\n"
				+ "                    \"dateOfBirth\": \"07-Jul-1983\"\r\n"
				+ "                }\r\n"
				+ "            ]\r\n"
				+ "        }\r\n"
				+ "    ]").exists());
		
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
}
