# 🛒 E-Commerce Microservices Platform

A scalable and cloud-ready E-Commerce application built using a microservices architecture. The system is designed for high availability, modularity, and performance, integrating modern backend technologies and DevOps practices.

## 🚀 Features

- User Registration & Login
- JWT-based Authentication & Authorization
- Product Management
- Shopping Cart Management
- Order Processing
- Secure Payment Gateway Integration
- Service Discovery for dynamic service registration
- API Gateway for centralized request routing
- Load Balancing for improved scalability
- Event-driven architecture using Apache Kafka
- Automated Welcome Email Service
- Containerization using Docker
- Deployment using Kubernetes
- Scalable Microservices Architecture
- Redis caching

---

## 🏗️ Architecture

The application follows a Microservices-based architecture:

```text
                    Client Request
                           |
                           ↓
                  API Gateway Service
                           |
        -------------------------------------
        |                                   |
        ↓                                   ↓
   User Service                         Product Service
        |                                   |
        -------------------------------------
                           |
                     Service Discovery
                           |
                      Database Layer
                           |
                    Payment Integration
                           |
                     Apache Kafka
                           |
                    Email Notification
```

---

## 🛠️ Tech Stack

### Backend
- Java
- Spring Boot
- Spring Security
- Spring Cloud

### Microservices Components
- API Gateway
- Service Discovery (Eureka)
- Load Balancer

### Authentication
- JWT (JSON Web Token)

### Messaging
- Apache Kafka
- Event-driven communication

### Payment Integration
- Payment Gateway API

### Database
- MySQL

### DevOps & Deployment
- Docker
- Kubernetes

### Other Tools
- Git
- GitHub
- Maven
- Postman

---

## 🔐 Authentication & Authorization

This project implements JWT-based authentication for securing APIs.

Features:
- User login and registration
- Token generation
- Protected endpoints
- Secure request validation

---

## 📧 Event-Driven Email Notification

Apache Kafka is used for asynchronous communication.

Workflow:

1. User registration completed
2. Event published to Kafka topic
3. Email service consumes event
4. Welcome email sent automatically

Benefits:
- Reduced service dependency
- Better scalability
- Improved system performance

---

## 📦 Containerization with Docker

Each microservice is containerized using Docker for portability and consistency.

---

## ☸️ Kubernetes Deployment

Kubernetes is used for orchestration and management of containers.

Features:
- Auto scaling
- Load balancing
- Service management
- Container orchestration
- High availability

---

## ⚡ API Gateway

API Gateway acts as a single entry point for all client requests.

Responsibilities:
- Request routing
- Authentication filtering
- Security
- Load balancing
- Service communication

---

## 🔄 Service Discovery

Service Discovery enables dynamic registration and communication between services.

Benefits:
- Automatic service registration
- Reduced hard-coded configurations
- Better scalability

---

## 📁 Project Structure

```text
Ecommerce-Microservices/
│
├── api-gateway/
├── service-discovery/
├── user-service/
├── product-service/
├── payment-service/
├── email-service/
├── docker/
├── kubernetes/
└── README.md
```

---

## 🎯 Future Enhancements

- Recommendation system
- Product reviews and ratings
- CI/CD pipeline integration
- Monitoring using Prometheus & Grafana
- Distributed tracing

---

## 👨‍💻 Author

Mukund Koshti

GitHub: https://github.com/MukundKoshti-12

LinkedIn: https://www.linkedin.com/in/mukund-koshti-35b29b271/

---

## 📄 License

This project is developed for learning and portfolio purposes.