# Hotel Microservice Project

A backend application built with **Java, Spring Boot, and Spring Cloud** using a microservices architecture.

The project is divided into separate services for users, hotels, and ratings.  
Each service has its own responsibility and communicates with other services when required.

---

## What Does This Project Do?

The application manages:

- Users
- Hotels
- Hotel ratings and feedback

A user can be associated with hotels and their ratings.

Instead of building everything as one large application, the project separates the functionality into independent services.

---

## Project Architecture

```text
                         Client
                           │
                           ▼
                  ┌─────────────────┐
                  │   API Gateway   │
                  │    Port 8084    │
                  └────────┬────────┘
                           │
          ┌────────────────┼────────────────┐
          │                │                │
          ▼                ▼                ▼
   ┌────────────┐   ┌────────────┐   ┌────────────┐
   │ User       │   │ Hotel      │   │ Rating     │
   │ Service    │   │ Service    │   │ Service    │
   │ Port 8081  │   │ Port 8082  │   │            │
   └──────┬─────┘   └──────┬─────┘   └──────┬─────┘
          │                │                │
          ▼                ▼                ▼
       MySQL           PostgreSQL        MongoDB


        ┌─────────────────────────────────────┐
        │          Eureka Service Registry    │
        │     Service Discovery & Registry    │
        └─────────────────────────────────────┘

        ┌─────────────────────────────────────┐
        │            Config Server            │
        │       Centralized Configuration     │
        └─────────────────────────────────────┘
```

## Services in the Project
1. API Gateway

The API Gateway acts as the single entry point for client requests.

Instead of directly calling every microservice, the client can send requests to the gateway.

Routes
Request	Service
/users/**	User Service
/hotels/**	Hotel Service
/ratings/**	Rating Service
Responsibilities
Receives incoming requests
Routes requests to the appropriate microservice
Handles authentication
Validates OAuth2/JWT tokens
Provides a common entry point for the application

Port: 8084

2. User Service

The User Service manages application users.

Available operations
Create a user
Get a single user
Get all users
Endpoints
Method	Endpoint	Purpose
POST	/users	Create a user
GET	/users	Get all users
GET	/users/{userId}	Get a specific user
Database

MySQL

The User Service can also communicate with the Hotel and Rating services to retrieve related information.

3. Hotel Service

The Hotel Service manages hotel information.

Available operations
Create a hotel
Get all hotels
Get a specific hotel
Endpoints
Method	Endpoint	Purpose
POST	/hotels	Create a hotel
GET	/hotels	Get all hotels
GET	/hotels/{hotelId}	Get a specific hotel
Hotel information includes
Hotel ID
Hotel name
Location
About
Database

PostgreSQL

4. Rating Service

The Rating Service manages ratings and feedback given by users for hotels.

Each rating contains:

Rating ID
User ID
Hotel ID
Rating
Feedback

This allows a rating to connect a user with a hotel.

Endpoints
Method	Endpoint	Purpose
POST	/ratings	Create a rating
GET	/ratings	Get all ratings
GET	/ratings/user/{userId}	Get ratings by user
GET	/ratings/hotels/{hotelId}	Get ratings for a hotel
Database

MongoDB

# Service Discovery with Eureka

The project uses Eureka Service Registry for service discovery.

Instead of hardcoding the address of every microservice, services register themselves with Eureka.

For example:

User Service
      │
      ▼
Eureka Server
      ▲
      │
Hotel Service

When one service needs to communicate with another, it can discover the service through Eureka.

This is especially useful when service locations or ports change.

# Centralized Configuration

The project contains a dedicated Config Server.

Instead of keeping every configuration value separately inside every microservice, configuration can be managed centrally.

The project therefore contains:

Config Server
     │
     ├── User Service
     ├── Hotel Service
     └── Other Services

This makes configuration easier to manage across multiple services.

## Security

The project uses Spring Security + OAuth 2.0 + Okta.

Authentication flow
User
 │
 ▼
API Gateway
 │
 │ OAuth2 / JWT
 ▼
Okta
 │
 │ Access Token
 ▼
API Gateway
 │
 ▼
Microservice
 │
 ▼
JWT validation
Security features
OAuth 2.0 authentication
Okta as the identity provider
JWT access tokens
Protected API endpoints
JWT validation at the API Gateway
JWT validation at individual services
OAuth2-based service-to-service authentication

Sensitive values such as:

Okta client secret
Database passwords

are kept outside the repository using environment variables.

Example:

client-secret: ${OKTA_CLIENT_SECRET}
password: ${DB_PASSWORD}

Actual credentials are not stored in this repository.

## Communication Between Services

The services communicate with each other when information from another service is required.

The project uses technologies such as:

OpenFeign
RestTemplate
Eureka Service Discovery

For example:

User Service
     │
     ├──────► Hotel Service
     │
     └──────► Rating Service

This allows the User Service to work with information maintained by other services without putting all the data into one database.

## Resilience and Fault Handling

The project uses Resilience4j to handle failures between services.

Circuit Breaker

If a dependent service becomes unavailable, the Circuit Breaker can prevent repeated failed requests.

Example:

User Service
     │
     ▼
Rating Service ❌ DOWN
     │
     ▼
Fallback Response

Instead of continuously waiting for a failed service, the application can provide a fallback response.

## Retry

The project also contains Retry configuration for temporary failures.

The idea is simple:

Request
   │
   ▼
Service fails
   │
   ▼
Try again
   │
   ▼
Try again
   │
   ▼
Final result / fallback
🚦 Rate Limiter

Rate Limiting controls how frequently requests can reach a service.

This helps prevent excessive requests from overwhelming the application.

# Databases

The project uses different databases for different services.

Service	Database
User Service	MySQL
Hotel Service	PostgreSQL
Rating Service	MongoDB

Each service can therefore manage its own data independently.

# Technologies Used
Backend
Java
Spring Boot
Spring Cloud
Spring MVC
Spring Data JPA
Microservices
Spring Cloud Gateway
Eureka Service Discovery
Spring Cloud Config Server
OpenFeign
RestTemplate
Security
Spring Security
OAuth 2.0
Okta
JWT
Databases
MySQL
PostgreSQL
MongoDB
Fault Tolerance
Resilience4j
Circuit Breaker
Retry
Rate Limiter
Build & Development
Maven
Lombok
Spring Boot DevTools
