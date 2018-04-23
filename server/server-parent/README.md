## 1. RT Alpha Server Port Allocation
- Config Server: 8000-8009, 8000 (reserved for load balancer), 8001-8009 (reserved for instances)
- Service Registry: 8010-8019, 8010 (reserved for load balancer), 8011-8019 (reserved for instances)
- API Gateway: 8020-8029, 8020 (reserved for load balancer), 8021-8029 (reserved for instances)

## 2. RT Alpha Client Port Range
- PMS Application: 9000-9099
- UMS Application: 9100-9199
- EMS Application: 9200-9199

## 3. Third Party Application Port Range
- MongoDB: 27017 (default)
- RabbitMQ: 5672 (default)