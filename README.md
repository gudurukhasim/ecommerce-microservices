# 🛒 E-Commerce Microservices Platform

A production-ready, event-driven microservices application built with **Java 17**, **Spring Boot 3.x**, **Apache Kafka**, **Redis**, and **Docker**.

---

## 🏗️ Architecture

<img width="527" height="577" alt="image" src="https://github.com/user-attachments/assets/fc85175b-9ed5-4f95-ab21-f85fea3daa89" />


Client → API Gateway (8080)
↓
┌─────────────────────────────────┐
│         Microservices           │
├──────────────┬──────────────────┤
│ User Service │ Product Service  │
│    :8081     │     :8082        │
├──────────────┴──────────────────┤
│         Order Service :8083     │
├─────────────────────────────────┤
│        Payment Service :8084    │
├─────────────────────────────────┤
│     Notification Service :8085  │
└─────────────────────────────────┘
↓ Kafka Events
┌─────────────────────────────────┐
│         Infrastructure          │
│  MySQL │ MongoDB │ Redis │ Kafka │
└─────────────────────────────────┘

---

## 🚀 Tech Stack

| Technology | Purpose |
|-----------|---------|
| Java 17 | Core language |
| Spring Boot 3.x | Microservices framework |
| Spring Cloud Gateway | API Gateway + JWT auth |
| Apache Kafka | Async event streaming |
| Redis | Caching + session management |
| MySQL | Relational data storage |
| MongoDB | Notification documents |
| Docker + Compose | Containerization |
| JUnit 5 + Mockito | Unit testing |
| JWT | Authentication |

---

## 📦 Services

### 1. API Gateway (:8080)
- Single entry point for all services
- JWT token validation
- Request routing
- Rate limiting with Redis

### 2. User Service (:8081)
- User registration and login
- JWT token generation
- BCrypt password encoding
- Redis session caching (24hr TTL)

### 3. Product Service (:8082)
- Product CRUD operations
- Redis caching (10min TTL)
- Cache invalidation on update/delete
- Category-based filtering

### 4. Order Service (:8083)
- Place and track orders
- Kafka producer → `order-placed` topic
- Kafka consumer ← `payment-success/failed` topics
- Order status management

### 5. Payment Service (:8084)
- Kafka consumer ← `order-placed` topic
- Mock payment processing (90% success rate)
- Kafka producer → `payment-success/failed` topics
- Payment record persistence

### 6. Notification Service (:8085)
- Kafka consumer ← `payment-success/failed` topics
- Mock email notifications
- MongoDB document storage

---

## 🔄 Event Flow
User places order
↓
Order Service → Kafka [order-placed]
↓
Payment Service consumes → processes payment
↓
Payment Service → Kafka [payment-success/failed]
↓
Order Service ← updates order status
Notification Service ← sends email notification

---

## 🛠️ Quick Start

### Prerequisites
- Docker Desktop
- Java 17+
- Maven 3.8+

### Run with Docker Compose

```bash
# Clone the repository
git clone https://github.com/YOUR_USERNAME/ecommerce-microservices.git
cd ecommerce-microservices

# Build all services
cd user-service/userservice && mvn clean package -DskipTests && cd ../..
cd product-service/productservice && mvn clean package -DskipTests && cd ../..
cd order-service/order-service && mvn clean package -DskipTests && cd ../..
cd payment-service/payment-service && mvn clean package -DskipTests && cd ../..
cd notification-service/notification-service && mvn clean package -DskipTests && cd ../..
cd api-gateway/api-gateway && mvn clean package -DskipTests && cd ../..

# Start everything
docker compose up --build
```

All services will be available at their respective ports.

---

## 📡 API Endpoints

### Auth (Public)

POST /api/auth/register  → Register new user
POST /api/auth/login     → Login and get JWT token

### Products (JWT Required)

GET    /api/products          → Get all products
GET    /api/products/{id}     → Get product by ID
POST   /api/products          → Create product
PUT    /api/products/{id}     → Update product
DELETE /api/products/{id}     → Delete product

### Orders (JWT Required)

POST /api/orders                    → Place order
GET  /api/orders                    → Get all orders
GET  /api/orders/{orderNumber}      → Get order by number
GET  /api/orders/user/{email}       → Get orders by user

### Payments (JWT Required)
GET /api/payments                   → Get all payments
GET /api/payments/order/{number}    → Get payment by order
GET /api/payments/user/{email}      → Get payments by user

### Notifications (JWT Required)

GET /api/notifications              → Get all notifications
GET /api/notifications/user/{email} → Get by user
GET /api/notifications/order/{number} → Get by order

---

## 🔐 Authentication

All protected endpoints require a JWT token:

```http
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...
```

Get a token by calling `/api/auth/login`.


---

## 📊 Key Design Patterns

| Pattern | Where Used |
|---------|-----------|
| API Gateway | Spring Cloud Gateway |
| Event-Driven | Kafka async messaging |
| Cache-Aside | Redis product caching |
| Saga | Order → Payment → Notification |
| Repository | Spring Data JPA/MongoDB |

---

## 🗄️ Database Design

| Service | Database | Collections/Tables |
|---------|----------|-------------------|
| User Service | MySQL | users |
| Product Service | MySQL | products |
| Order Service | MySQL | orders, order_items |
| Payment Service | MySQL | payments |
| Notification Service | MongoDB | notifications |

---

## 👤 Author

**Your Name**  
Java Backend Developer | 3 Years Experience  
📧 gudurukhasim@gmail.com  
🔗 [LinkedIn](https://www.linkedin.com/in/khasim-guduru-906505242)  
🐙 [GitHub](https://github.com/gudurukhasim?tab=repositories)

---

## ⭐ If you found this helpful, please give it a star!
