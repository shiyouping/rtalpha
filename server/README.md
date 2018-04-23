## **1. Overview**
Originally server module is based on service-oriented architecture, I have been refactoring them to micro-service style since this March. For the time being, server module has the following services:

1.1. Config Server<br>
1.2. Service Registry<br>
1.3. API Gateway<br>
1.4. Product Management System<br>
1.5. User Management System (in the progress of refactoring)<br>
1.6. Email Management System<br> 

New services such as Payment Management System, Order Management System, etc, will be added gradually. 

## **2. Design**
Please see documents of architecture and UML in [doc](https://github.com/shiyouping/rtalpha/tree/master/doc) directory to understand how server applications work and interact with each other.

## **3. In Progress**
3.1. Integrate Spring Zuul to the server of API Gateway.<br>
3.2. Implement OAuth 2.0 for different services.<br>

## **4. To Do**
