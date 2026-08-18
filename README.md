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
