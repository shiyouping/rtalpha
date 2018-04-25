
##  Server Architecture

The following diagram illustrates the overview of server module's architecture, each component of which will be explained in details in the next sections. Without explicitly stated, the blue clusters use server-side load balancers to increase their reliability, and the communication protocol between different clusters is based on HTTP by using REST calls. To ease and facilitate the deployment, all services are dockerized to run on the same or different servers.

![Architecture Overview](https://github.com/shiyouping/rtalpha/blob/master/doc/architecture/server.png)

##  1. Config Server Cluster
Config Server keeps the externalized configuration for all other services, e.g. Service Registry, API Gateway, etc. It must be the first service to start up before other server applications, and loads all the client configuration from a version control system to memory, so that config clients, e.g. Email Management System, are able to retrieve their own configuration while they are starting. Once Config Server is started, it keeps monitoring the configuration files and reloads them once any changes are detected. Then it notifies other services via a message broker, e.g. RabbitMQ, to re-obtain the latest configuration to keep config clients updated. Realtime configuration updating is really important in a micro-service environment, but this feature can be disabled and config clients are able to use local configuration in the phase of development.

## 2. Service Registry Cluster
After Config Server gets started, Service Registry has to be the second service to run as a service discovery service. When a service other then Config Server or Service Registry is up, it immediately registers itself in Service Registry, and also gets the addresses of its dependent services. Afterwards, client services keep sending heartbeat signals to Service Registry which will notify its clients once a service is dead. 

## 3. API Gateway Cluster
API Gateway is the only entry point exposed to web and mobile applications. All client requests outside are sent here and routed to corresponding back end services. Since API Gateway acts as a filter and router, some important functionalities are implemented here, such as authentication, logging and monitoring, client rate limiting, response caching, etc. 

(To Be Continued)
