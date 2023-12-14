/**
 * 
 */
package com.devhabit.departmentservice.service.client.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancedExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import com.devhabit.departmentservice.service.client.EmployeeClient;

import io.netty.resolver.DefaultAddressResolverGroup;
import reactor.netty.http.client.HttpClient;

/**
 * Client to connect to employee service from service registry
 * 
 */
@Configuration
public class WebClientConfig {

	@Autowired
	private LoadBalancedExchangeFilterFunction filter;
	
	@Bean
	public WebClient employeeWebClient() {
		return WebClient.builder().clientConnector(new ReactorClientHttpConnector(
                        HttpClient.create()
                                .resolver(DefaultAddressResolverGroup.INSTANCE)))
				.baseUrl("http://employee-service")
				.filter(filter)
				.build();
	}
	
	@Bean 
	public EmployeeClient employeeClient() {
		
		HttpServiceProxyFactory proxyFactory = HttpServiceProxyFactory.builder(
				WebClientAdapter.forClient(employeeWebClient())
				).build();
		return proxyFactory.createClient(EmployeeClient.class);
	}
}
