# API Gateway

A reusable API Gateway built with Java 21, Spring Boot 3.4.4, and Gradle (Kotlin DSL) that leverages Spring Cloud Gateway and Eureka for dynamic service routing.

## Overview

The **api-gateway** project sets up a centralized gateway for routing, monitoring, and managing cross-cutting concerns (such as authentication, CORS, and rate limiting) in your Spring Boot microservices environment. Designed for easy cloning and integration, this gateway uses Spring Cloud Gateway with Eureka-based service discovery.

## Tech Stack

- **Java:** 21
- **Spring Boot:** 3.4.4
- **Spring Cloud Gateway**
- **Spring Cloud Eureka Client**
- **Gradle (Kotlin DSL)**

## Getting Started

1. **Clone the repository:**

   ```bash
   git clone https://github.com/your-username/api-gateway.git

2. **Navigate to the project folder:**

   ```bash
   cd api-gateway

3. **Run the application:**

   ```bash
   ./gradlew bootRun

4. **Access the Gateway:**

   The gateway runs on http://localhost:8080

## Configuration

Key configuration settings are provided in src/main/resources/application.yml. Verify the Eureka server URL and other properties to match your local or production environment.

## License

This project is distributed under the MIT License.

