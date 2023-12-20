/**
 * 
 */
package com.devhabit.departmentservice.controller.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

import com.devhabit.departmentservice.controller.DepartmentController;
import com.devhabit.departmentservice.model.Department;
import com.devhabit.departmentservice.repository.DepartmentRepository;
import com.devhabit.departmentservice.service.DepartmentService;

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
		.andExpect(MockMvcResultMatchers.status().isOk());

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
								+ "    }").exists());
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
				+ "    ]").exists());
		
	}
}
