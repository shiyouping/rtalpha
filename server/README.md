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

## **2. System Architecture**
![System Architecture](https://github.com/shiyouping/rtalpha/blob/master/doc/architecture/server.png)

Please see the documents at [architecture](https://github.com/shiyouping/rtalpha/tree/master/doc/architecture) directory to understand how server applications work and interact with each other.

## **3. Maven Hierarchy**
![Maven Hierarchy](https://github.com/shiyouping/rtalpha/blob/master/doc/maven/inheritance.png)
Please see the documents at [maven](https://github.com/shiyouping/rtalpha/tree/master/doc/maven) directory to understand the hierarchy of maven poms.

## **4. In Progress**
- Integrate Spring Zuul to the server of API Gateway.

## **5. To Do**
- Implement OAuth 2.0 for different services.
- Integrate Swagger.
- Refactor User Management System to new architecture.