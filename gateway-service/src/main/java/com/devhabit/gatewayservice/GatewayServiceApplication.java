package com.devhabit.gatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(GatewayServiceApplication.class);
		app.setWebApplicationType(WebApplicationType.REACTIVE); 
		ConfigurableApplicationContext ctx = app.run(args);
		//SpringApplication.run(GatewayServiceApplication.class, args);

	}

}
