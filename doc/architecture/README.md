## Abstract
The following diagram illustrates the overview of server module's architecture, each component of which will be explained in details in the next sections. Without explicitly stated, the blue clusters use server-side load balancers to increase their reliability, and all clusters make use of Docker to ease the deployment on the same or different servers.
 
![Architecture Overview](https://github.com/shiyouping/rtalpha/blob/master/doc/architecture/overview.png)

## 1. Config Server Cluster
To externalize all the configuration information of services, Config Server keeps the configuration for all other services, e.g. Service Registry, API Gateway, etc.

(To Be Continued)