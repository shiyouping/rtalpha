
## **1. Overview**
Originally server module is based on service-oriented architecture, and I have been refactoring server applications to micro-service style since this March. Currently server module has covered the following micro-service components:

- API gateway
- Service discovery
- Externalized configuration
- Circuit breaker
- Health check API
- Service Integration Contract Test

Other components will be integrated gradually such as:

- Log aggregation
- Application metrics
- Audit logging
- Distributed tracing
- Exception tracking


For the time being, server module has the following services:

- Config Server
- Service Registry
- API Gateway
- Product Management System
- User Management System
- Email Management System

New services such as Payment Management System, Order Management System, etc, will be added in the near future. 

## **2. Design**
Please see documents of architecture and UML in [doc](https://github.com/shiyouping/rtalpha/tree/master/doc/architecture) directory to understand how server applications work and interact with each other.

## **3. In Progress**
- Integrate Spring Zuul to the server of API Gateway.

## **4. To Do**
- Implement OAuth 2.0 for different services.
- Integrate Swagger.
- Refactor User Management System to new architecture.