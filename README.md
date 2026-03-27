# Product Service Documentation

## Overview
The Product Service is a microservice that manages products in a distributed system. It leverages Spring Boot 3.5.9 for its framework, Java 17 for the programming language, and MySQL for data persistence.

## Technology Stack
- **Framework**: Spring Boot 3.5.9
- **Language**: Java 17
- **Database**: MySQL
- **Service Discovery**: Eureka
- **Configuration Management**: Spring Cloud Config

## Features
- CRUD operations for product management
- Integration with MySQL for data storage
- Service registration and discovery with Eureka
- Externalized configuration with Spring Cloud Config

## Getting Started
### Prerequisites
- Java 17 installed on your machine.
- MySQL 8.0 or later.
- Maven for dependency management.

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/sibashish726/ProductService.git
   cd ProductService
   ```
2. Configure your application properties for MySQL:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/productdb
   spring.datasource.username=root
   spring.datasource.password=yourpassword
   ```

3. Run the application:
   ```bash
   mvn spring-boot:run
   ```

## API Reference
### Get All Products
- **URL**: `/api/products`
- **Method**: `GET`

### Create a Product
- **URL**: `/api/products`
- **Method**: `POST`
- **Request Body**:
  ```json
  {
      "name": "Product Name",
      "price": 100.00,
      "description": "Product Description"
  }
  ```

### Error Handling
The service will respond with standard error codes for the following conditions:
- 400 BAD REQUEST - when the request is invalid
- 404 NOT FOUND - when a product is not found

## Running Tests
Run the following command to execute tests:
```bash
mvn test
```

## Conclusion
The Product Service is designed to manage product information effectively, providing a robust API with a solid technology stack.

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.