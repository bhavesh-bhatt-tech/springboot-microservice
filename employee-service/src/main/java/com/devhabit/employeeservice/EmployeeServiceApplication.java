package com.devhabit.employeeservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EmployeeServiceApplication {
	private static final Logger log = LoggerFactory.getLogger(EmployeeServiceApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(EmployeeServiceApplication.class, args);
		log.info("Arguments = {}", args[0]);
	}

}
