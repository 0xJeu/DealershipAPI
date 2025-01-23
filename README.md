# Dealership REST API

A Spring Boot application that provides REST API endpoints for managing a car dealership's operations.

## Project Overview

This is a Spring Boot-based REST API service designed to handle car dealership operations. The project uses Java 17 and is built with Maven.

## Technical Stack

- Java 17
- Spring Boot 3.4.0
- MySQL Database
- Apache Commons DBCP2 for database connection pooling
- Maven for dependency management

## Prerequisites

To run this project, you need:

- Java 17 or higher
- Maven
- MySQL Database

## Project Setup

1. Clone the repository
2. Configure your MySQL database connection in `application.properties`
3. Build the project:
   ```bash
   ./mvnw clean install
   ```
4. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```

## Database Configuration

Make sure to configure your database connection in `src/main/resources/application.properties` with your MySQL credentials.

## Building

To build the project:

```bash
./mvnw clean package
```

This will create a JAR file in the `target` directory.

## Development

This project follows standard Spring Boot project structure:

- `src/main/java` - Application source code
- `src/main/resources` - Configuration files
- `src/test` - Test cases

## Contributing

1. Fork the repository
2. Create your feature branch
3. Commit your changes
4. Push to the branch
5. Create a new Pull Request

## License

This project is licensed under the terms of the license provided with the project.
