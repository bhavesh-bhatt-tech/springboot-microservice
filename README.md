
# Spring boot 3 microservice with Spring cloud
The project is microservice development using spring boot 3 along with below components


## Features

- Eureka Service Registry
- Spring cloud config server
- Spring cloud Gateway
- Spring Webclient(Fluent API)
- Distributed tracing with Zipkin
- Unit testing with Mockito Junit Jupiter
- Code coverage with Jacoco


## Installation

Follow below order of execution to start each service


    1. service registry
    2. config server
    3. gateway server 
    4. department service
    5. employee service
Go to the respective project directory and execute below command
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

clean jacoco:prepare-agent install jacoco:report

## Blog
step by step explation of project development

https://www.devhabit.org/post/spring-boot-3-microservice-spring-cloud