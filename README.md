# Food Delivery System

A microservices-based food delivery application built with:

- Spring Boot
- Spring Cloud Gateway
- Kafka (asynchronous event communication)
- Redis (caching & token storage)
- OracleDB (relational database)
- Docker & Docker Compose (containerization)

## Microservices

| Service              | Description                                    |
|----------------------|------------------------------------------------|
| auth-service         | User registration, login, JWT, token in Redis |
| user-service         | Manage user profile info                      |
| restaurant-service   | CRUD for menu items, Redis menu cache         |
| order-service        | Create orders, Kafka publish `order.created`  |
| payment-service      | Kafka consumer, return `payment.completed`    |
| delivery-service     | Assign shipper info, consume order events     |
| notification-service | Consume events, send emails                   |
| gateway-service      | API gateway using Spring Cloud Gateway        |

## Run with Docker

```bash
docker-compose up --build
```