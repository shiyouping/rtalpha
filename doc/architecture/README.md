
#  Server Architecture

The following diagram illustrates the overview of server module's architecture, each component of which will be explained in details in the next sections. Without explicitly stated, the blue clusters, e.g. API Gateway Cluster, use server-side load balancers to increase their reliability, and the communication protocol between different services is based on HTTP by using REST calls. To accelerate the data processing, a distributed in-memory key/value data store, e.g. Infinispan, is applied to synchronize caches for services. To ease and facilitate the deployment, all services are dockerized to run on the same or different servers.

![Server Architecture](https://github.com/shiyouping/rtalpha/blob/master/doc/architecture/server.png)

##  1. Config Server Cluster
Config Server keeps the externalized configuration for all other services, e.g. Service Registry, API Gateway, etc. It must be the first service to start up before other server applications, and loads all the client configuration from a version control system to memory, so that config clients, e.g. Email Management System, are able to retrieve their own configuration while they are starting. Once Config Server is started, it keeps monitoring the configuration files and reloads them once any changes are detected. Then it notifies its clients via a message broker, e.g. RabbitMQ, to re-obtain the latest configuration to keep updated. Realtime configuration updating is really important in a micro-service environment, but this feature can be disabled and config clients are able to use their own local configuration in the phase of development.

## 2. Service Registry Cluster
After Config Server gets started, Service Registry has to be the second one to run as a service discovery service. When a service other then Config Server or Service Registry is up, it immediately registers itself with Service Registry, and also gets the addresses of its dependent services. Afterwards, client services keep sending heartbeat signals to Service Registry which in turn will notify its clients once a service is dead. 

## 3. API Gateway Cluster
API Gateway is the only entry point exposed to external web and mobile applications. All external client requests are sent here and routed to corresponding back end services. Since API Gateway acts as a filter and router, some important functionalities are implemented here, such as authentication, logging and monitoring, client rate limiting, response caching, etc. Client requests get to API Gateway through server side load balancer. As all server services except Config Server and Service Registry register themselves with Service Registry, API Gateway is able to keep a list of living back end services from Service Registry and uses client side load balancer, e.g. Ribbon from Netflix, to route client requests to their destination service.

## 4. Service Cluster
Generally speaking, different service clusters use different databases to achieve loose coupling. In the architecture diagram, Service A Cluster uses a NoSQL database and Service B Cluster uses a traditional SQL database. But that is not always the case. Service B cluster is allowed to share the same database with Service C Cluster if the requirement needs. Each service has its own single responsibility to process business requests. For example, Product Management System only handles client requests about products, and Email Management System focuses on email processing. Each service runs in an isolated process and can request resources from other services via HTTP/REST calls.  When one or more services are unavailable or exhibiting high latency, it results in a cascading failure across the enterprise. Service client retry logic only makes things worse for the service, and can bring the whole system down completely. So Circuit Breaker mechanism is applied to avoid such a cascading failure. For instance, when there is something wrong with Email Management System, User Management System requests an validation email from Email Management System, it will get a fallback response which indicates that Email Management System cannot handle any requests for the time being. Just like API Gateway uses client side load balancer to route requests, each service in one cluster also uses the same load balancer to communicate with other services in other clusters. For example, Instance One of Service A uses built-in Ribbon load balancer to send a request to Instance One of Service B this time, but next time Instance One of Service A may send another request to Instance Two of Service B.

(To Be Continued)

# Web Architecture

![Architecture](https://github.com/shiyouping/rtalpha/blob/master/doc/architecture/web.png)

## 1. Overview

RTAlpha Web is designed with the `component-oriented` concept, and composed of `UI Layer`, `Middle Layer` and `Non-UI Layer`.

| Layer        | React Based | Automation Test |
| ------------ | :---------: | --------------: |
| UI Layer     |     Yes     |       Difficult |
| Middle Layer |   Partial   |          Median |
| Non-UI Layer |     No      |            Easy |

### 1.1. UI Layer

This layer relies heavily on HTML, CSS and React. It is where end users interact with RTAlpha Web, so it is not that easy to apply unit testing and other automation testing to this layer.

### 1.2. Middle Layer

This layer is where RTAlpha Web transits from UI Layer to Non-UI Layer. Therefore, there may be UI and Non-UI code mixed in one component.

### 1.3. Non-UI Layer

This layer is a `pure JavaScript` layer and isolated from React. The automation testing, e.g. unit testing will be applied to this layer to facilitate CI/CD.

## 2. Hierarchy

From the architecture diagram, UI Layer is based on Middle Layer and Non-UI Layer, and Middle Layer relies on Non-UI Layer. Each layer is composed of different components respectively.

Components on the same level are marked with the same colors. The components are visible to those components which are on the same or higher levels. That means components are able to call other components which are on the same or lower levels. For example, REST Service and WebSocket Service are allowed to call each other, and both of them are legal to use SharedStore. However, REST Service will never utilize React Framework or Application Level Service.

## 3. Storage

There are two kinds of global Storage, i.e. Redux and SharedStore. If data is intended to cause page to self-update, it should be kept in Redux, otherwise SharedStore is the right place.

## 4. Model & View

Views on UI Layer are `ONLY` allowed to update virtual DOM via REST Service, WebSocket Service, Redux and SharedStore, i.e. views change data, and data being changed will cause UI to refresh itself.