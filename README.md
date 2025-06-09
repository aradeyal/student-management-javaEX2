# Student Management System (Spring Boot)

A simple Spring Boot application for managing student data using RESTful APIs.  
Supports operations like adding, listing, and managing students with proper validation and error handling.

## Features

- CRUD operations on students
- JPA with MySQL integration
- Exception handling with custom responses
- Data validation using annotations
- Standardized API response format

## Tech Stack

- Java 17
- Spring Boot 3.5.0
- Spring Data JPA
- Lombok
- MySQL
- Postman (for testing)

## Getting Started

### Prerequisites

- Java 17
- Maven
- MySQL running on localhost

### Configuration

In the file `src/main/resources/application.properties`, configure:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/YOUR_DB_NAME
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
