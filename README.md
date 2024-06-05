Certainly! Here's a comprehensive README for your GitHub repository:

---

# Spring User Microservice with Custom Auth Annotation

This project is a Spring Boot microservice that demonstrates user management with custom authentication annotations. It includes features like user registration, JWT token generation, and authentication using custom annotations.

## Table of Contents

- [Features](#features)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Usage](#usage)
- [Endpoints](#endpoints)
- [Custom Annotations](#custom-annotations)
- [Security](#security)
- [Contributing](#contributing)
- [License](#license)

## Features

- User Registration
- JWT Token Generation
- Custom Authentication Annotations
- Secure Endpoints with JWT
- Error Handling for Duplicate Users

## Prerequisites

- Java 11 or higher
- Maven
- PostgreSQL (or any other relational database)
- Git

## Installation

1. **Clone the repository:**

    ```bash
    git clone https://github.com/glovistts/spring_user_microservice_custom_auth_annotation.git
    cd spring_user_microservice_custom_auth_annotation
    ```

2. **Configure the database:**

    Update the `application.properties` file with your database configuration:

    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/your_database
    spring.datasource.username=your_username
    spring.datasource.password=your_password
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    ```

3. **Build the project:**

    ```bash
    mvn clean install
    ```

4. **Run the application:**

    ```bash
    mvn spring-boot:run
    ```

## Usage

After running the application, you can interact with the microservice using tools like Postman or cURL. The Swagger UI is also available at `http://localhost:8080/swagger-ui.html` for API documentation and testing.

## Endpoints

### User Registration

- **POST /register**

  Registers a new user.

  **Request Body:**
  ```json
  {
    "username": "johndoe",
    "email": "johndoe@example.com",
    "phone": "1234567890",
    "password": "securepassword"
  }
  ```

### JWT Token Generation

- **POST /login**

  Authenticates a user and returns a JWT token.

  **Request Body:**
  ```json
  {
    "username": "johndoe",
    "password": "securepassword"
  }
  ```

### Secured Endpoint Example

- **GET /secured-endpoint**

  Requires a valid JWT token.

  **Request Header:**
  ```
  Authorization: Bearer <JWT_TOKEN>
  ```

## Custom Annotations

The project includes custom annotations for securing endpoints. Example:

```java
@RestController
public class UserController {

    @CustomAuth // Custom annotation for securing the endpoint
    @GetMapping("/secured-endpoint")
    public String securedEndpoint() {
        return "This is a secured endpoint";
    }
}
```

## Security

JWT (JSON Web Token) is used for securing endpoints. The token is generated using `jjwt` library and includes claims such as username and roles.

### Generating JWT Token

The token is generated with the following method:

```java
public String generateToken(Map<String, Object> claims) {
    try {
        return Jwts.builder()
                   .setClaims(claims)
                   .setIssuedAt(new Date(System.currentTimeMillis()))
                   .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                   .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                   .compact();
    } catch (Exception e) {
        logger.error("Error generating JWT token", e);
        throw new RuntimeException("Failed to generate JWT token", e);
    }
}
```

## Contributing

Contributions are welcome! Please open an issue or submit a pull request for any bugs or feature requests.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

This README provides an overview of the project, including setup instructions, usage examples, and explanations of key features. Feel free to customize it further to fit your specific needs.
