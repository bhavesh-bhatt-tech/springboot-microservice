/**
 * 
 */
package com.devhabit.employeeservice.controller.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.devhabit.employeeservice.controller.EmployeeController;
import com.devhabit.employeeservice.model.Employee;
import com.devhabit.employeeservice.repository.EmployeeRepository;
import com.devhabit.employeeservice.service.EmployeeService;

/**
 * 
 */
@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerTest {

	private static final Logger log = LoggerFactory.getLogger(EmployeeControllerTest.class);
	@Autowired
	MockMvc mockMvc;
	
	@Mock
	private EmployeeController employeeController;
	
	@Mock
	private EmployeeService employeeService;
	
	@Mock
	private EmployeeRepository employeeRepository;

	
	List<Employee> employeeList = new ArrayList<>();
	
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
	void addEmployeeTest() throws Exception {
		log.info("addEmployeeTest started");

		Employee employee = new Employee(1L, "Sumeet","Shah");	
		employee.setDateOfBirth("05-Nov-1984");
		Mockito.when(employeeService.save(employee)).thenReturn(employee);
		Mockito.when(employeeRepository.add(employee)).thenReturn(employee);
		mockMvc.perform(MockMvcRequestBuilders.post("/employee/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\r\n"
						+ "    \"id\" : \"1\",\r\n"
						+ "    \"firstName\" : \"Sumeet\",\r\n"
						+ "    \"lastName\" : \"Shah\",\r\n"
						+ "    \"dateOfBirth\" : \"05-Nov-1984\",\r\n"
						+ "    \"departmentId\": \"1\"\r\n"
						+ "}")).andExpect(MockMvcResultMatchers.status().isOk())
		
				.andExpect(MockMvcResultMatchers.jsonPath("data","{\r\n"
						+ "        \"id\": 1,\r\n"
						+ "        \"departmentId\": 1,\r\n"
						+ "        \"firstName\": \"Sumeet\",\r\n"
						+ "        \"lastName\": \"Shah\",\r\n"
						+ "        \"dateOfBirth\": \"05-Nov-1984\"\r\n"
						+ "    }").exists());
	}
	
	@Test
	void findAllTest() throws Exception {
		log.info("findAllTest started");
		Employee Employee = new Employee(1L, "Sumeet","Shah");		
		Employee Employee2 = new Employee(1L, "Chirag","Maru");
		employeeList.add(Employee);
		employeeList.add(Employee2);
		
		Mockito.when(employeeService.findAll()).thenReturn(employeeList);
		Mockito.when(employeeRepository.findAll()).thenReturn(employeeList);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/employee/all")
				.contentType(MediaType.APPLICATION_JSON)
				).andExpect(MockMvcResultMatchers.status().isOk())
		
		.andExpect(MockMvcResultMatchers.jsonPath("data","[\r\n"
				+ "        {\r\n"
				+ "            \"id\": 1,\r\n"
				+ "            \"departmentId\": 1,\r\n"
				+ "            \"firstName\": \"Sumeet\",\r\n"
				+ "            \"lastName\": \"Shah\",\r\n"
				+ "            \"dateOfBirth\": \"05-Nov-1984\"\r\n"
				+ "        },\r\n"
				+ "        {\r\n"
				+ "            \"id\": 2,\r\n"
				+ "            \"departmentId\": 1,\r\n"
				+ "            \"firstName\": \"Chirag\",\r\n"
				+ "            \"lastName\": \"Maru\",\r\n"
				+ "            \"dateOfBirth\": \"15-Sep-1986\"\r\n"
				+ "        }\r\n"
				+ "    ]").exists());
		
	}
	@Test
	void findByEmployeeId() throws Exception{
		log.info("findByDepartmentId started");
		Employee employee = new Employee(1L, "Sumeet","Shah");
		
		employeeController.add(employee);
		Mockito.when(employeeService.findById(1L)).thenReturn(employee);
		Mockito.when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
		Mockito.when(employeeRepository.findById(1L)).thenReturn(Optional.empty());
		
		mockMvc.perform(MockMvcRequestBuilders.get("/employee/{id}", 1L)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("data").value(IsNull.nullValue()));
	}
	@Test
	void findByDepartmentId() throws Exception{
		log.info("findByDepartmentId started");
		
		Employee employee = new Employee(1L, "Sumeet","Shah");		
		Employee employee2 = new Employee(1L, "Chirag","Maru");
		employee.setDateOfBirth("05-Nov-1984");
		employee2.setDateOfBirth("15-Sep-1986");
		
		employeeList.add(employee);
		employeeList.add(employee2);
		
		Mockito.when(employeeService.findByDepartmentId(1L)).thenReturn(employeeList);
		Mockito.when(employeeRepository.findByDepartmentId(1L)).thenReturn(employeeList);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/employee/department/{departmentId}",1L)
				.contentType(MediaType.APPLICATION_JSON)
				).andExpect(MockMvcResultMatchers.status().isOk())
		
		.andExpect(MockMvcResultMatchers.jsonPath("data","[\r\n"
				+ "        {\r\n"
				+ "            \"id\": 1,\r\n"
				+ "            \"departmentId\": 1,\r\n"
				+ "            \"firstName\": \"Sumeet\",\r\n"
				+ "            \"lastName\": \"Shah\",\r\n"
				+ "            \"dateOfBirth\": \"05-Nov-1984\"\r\n"
				+ "        },\r\n"
				+ "        {\r\n"
				+ "            \"id\": 2,\r\n"
				+ "            \"departmentId\": 1,\r\n"
				+ "            \"firstName\": \"Chirag\",\r\n"
				+ "            \"lastName\": \"Maru\",\r\n"
				+ "            \"dateOfBirth\": \"15-Sep-1986\"\r\n"
				+ "        }\r\n"
				+ "    ]").exists());
	}
}
