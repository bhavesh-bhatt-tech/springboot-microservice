# Spring Boot 3 Microservices with Spring Cloud

## Project Description
This repository contains a robust, enterprise-grade demonstration of a microservice architecture built using **Spring Boot 3** and **Spring Cloud**. The project implements core microservice patterns including centralized configuration management, dynamic service discovery and registration, API gateway routing, non-blocking inter-service communication with Spring WebClient, and distributed tracing. It consists of multiple independent services (`config-server`, `service-registry`, `gateway-service`, `employee-service`, and `department-service`) designed to work seamlessly together.

---

## Technology Stack
- **Core Framework:** Spring Boot 3.2.1 (Java 17)
- **Microservices & Cloud:** Spring Cloud 2023.0.0
  - **Service Discovery:** Netflix Eureka Server & Client
  - **Configuration Management:** Spring Cloud Config Server (Native Profile)
  - **API Gateway:** Spring Cloud Gateway
- **Inter-Service Communication:** Spring WebClient (Reactive & Fluent API)
- **Observability & Distributed Tracing:** Micrometer Tracing (Brave) & Zipkin
- **Testing & Quality Assurance:** 
  - JUnit 5 & Mockito
  - JaCoCo (Code Coverage)
  - SonarCloud (Static Code Analysis)
- **Containerization & CI/CD:** Docker, Docker Compose, GitHub Actions

---

## Blog
Step-by-step explanation of project development:

https://www.devhabit.org/post/spring-boot-3-microservice-spring-cloud

---
# SonarQube Scan
[![SonarQube Cloud](https://sonarcloud.io/images/project_badges/sonarcloud-light.svg)](https://sonarcloud.io/summary/new_code?id=bhavesh-bhatt-tech_springboot-microservice)

# Sonar Quality Gate
[![Quality gate](https://sonarcloud.io/api/project_badges/quality_gate?project=bhavesh-bhatt-tech_springboot-microservice)](https://sonarcloud.io/summary/new_code?id=bhavesh-bhatt-tech_springboot-microservice)

# Github Action 
![Github workflow](https://github.com/bhavesh-bhatt-tech/springboot-microservice/actions/workflows/build.yml/badge.svg)

# Code Coverage
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=bhavesh-bhatt-tech_springboot-microservice&metric=coverage)](https://sonarcloud.io/summary/new_code?id=bhavesh-bhatt-tech_springboot-microservice)

# Reliability Ratings
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=bhavesh-bhatt-tech_springboot-microservice&metric=reliability_rating)](https://sonarcloud.io/summary/new_code?id=bhavesh-bhatt-tech_springboot-microservice)

# Technical Debt
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=bhavesh-bhatt-tech_springboot-microservice&metric=sqale_index)](https://sonarcloud.io/summary/new_code?id=bhavesh-bhatt-tech_springboot-microservice)

# Security Ratings
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=bhavesh-bhatt-tech_springboot-microservice&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=bhavesh-bhatt-tech_springboot-microservice)

# Maintainability Ratings
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=bhavesh-bhatt-tech_springboot-microservice&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=bhavesh-bhatt-tech_springboot-microservice)

# Bug 
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=bhavesh-bhatt-tech_springboot-microservice&metric=bugs)](https://sonarcloud.io/summary/new_code?id=bhavesh-bhatt-tech_springboot-microservice)

## Features

- Eureka Service Registry
- Spring cloud config server
- Spring cloud Gateway
- Spring Webclient(Fluent API)
- Distributed tracing with Zipkin
- Unit testing with Mockito Junit Jupiter
- Code coverage with Jacoco

# Installation

## Docker
go to root directory and execute below command

```bash
docker-compose up -d
```
## Maven
Go to the respective project directory in each project and execute below command
```bash
./mvnw spring-boot:run
```
## API Reference

#### Add department

```http
  POST /department/add
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `id` | `string` | **Required**. department id |
| `name` | `string` | **Required**. department name |

#### Add employee

```http
  POST /employee/add
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`   | `string` | **Required**. employee id |
| `departmentId`    | `string` | **Required**. department id |
| `firstName`   | `string` | **Required**. employee first name |
| `lastName`   | `string` | employee last name |
| `dateOfBirth`   | `string` | employee date of birth |

#### GET department

```http
  GET /department/{id}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `id` | `string` | **Required**. department id |

#### GET employee

```http
  GET /employee/{id}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `id` | `string` | **Required**. employee id |

#### GET all department

```http
  GET /department/all
```

#### GET employee by departmentId

```http
  GET /employee/department/{id}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `id` | `string` | **Required**. department id |

#### GET all department and employees

```http
  GET /department/all/department-employee
```

## Code Coverage
Run below commands to generate code coverage reports
```bash
clean jacoco:prepare-agent install jacoco:report
```
