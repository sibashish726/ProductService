# Product Service Documentation

## High-Level Design
The Product Service is designed to manage products with functionalities such as CRUD operations. It utilizes Spring Boot 3.5.9, Java 17, and MySQL as the database.

### Architecture
- **Microservices Architecture**: The Product Service is part of a larger microservices ecosystem that includes Eureka service discovery and Spring Cloud Config for configuration management.
- **Service Discovery**: Eureka is used for locating services for the purpose of load balancing and failover of middle-tier servers.

## Detailed Documentation

### 1. Overview
The Product Service provides a RESTful API for managing products, including creating, updating, retrieving, and deleting product entries.

### 2. Technologies Used  
- **Spring Boot**: Framework for building the Java-based RESTful web services.  
- **Java 17**: The programming language used.  
- **MySQL**: The database used for persistent storage.  
- **Eureka**: Service discovery for locating services in the architecture.  
- **Spring Cloud Config**: Manages configurations across different environments.

### 3. Endpoints
- **GET /products**: Retrieve all products.  
- **POST /products**: Create a new product.  
- **GET /products/{id}**: Retrieve a product by ID.  
- **PUT /products/{id}**: Update an existing product.  
- **DELETE /products/{id}**: Delete a product by ID.

### 4. Database Schema
- Products Table: Contains fields such as `id`, `name`, `description`, `price`, and `quantity`.

### 5. Configuration
Configuration properties are managed via Spring Cloud Config, making it easy to configure applications in different environments without modifying the code.  

### 6. Running the Application
1. Clone the repository.  
2. Configure the database in `application.yml`.  
3. Run the application using your IDE or command line.

### 7. Conclusion
This Product Service acts as a vital component of the inventory management system, facilitating the efficient management of products within microservices architecture.