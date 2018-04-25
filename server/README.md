## **1. Overview**
Originally server applications were based on service-oriented architecture, and I have been refactoring them to micro-service style since March 2018. Currently server module has covered the following micro-service components:

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


So far there are six server services:

- Config Server
- Service Registry
- API Gateway
- Product Management System
- User Management System
- Email Management System

New services, e.g. Payment Management System, Order Management System, etc, will be added in the future. 

## **2. Design**
Please see documents of architecture in [doc](https://github.com/shiyouping/rtalpha/tree/master/doc/architecture) directory to understand how server applications work and interact with web and mobile applications.

## **3. In Progress**
- Integrate Spring Zuul to the server of API Gateway.

## **4. To Do**
- Implement OAuth 2.0 for different services.
- Integrate Swagger.
- Refactor User Management System to new architecture.