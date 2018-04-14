WFM - employee audit
==================

This is a spring boot microservices application to demonstrate asynchronous communication between services via Apache Kafka broker. Each component is deployed in a docker container.

There are two microservices:
- Employee - this services handles CRUD operations on an Employee and sends audit messages to Kafka. It
  uses the `KafkaTemplate`.
- Audit - this service listens to audit topics on Kafka and persists the audit data. It will also retreive audit data. It uses `@KafkaListener`.

Technologies
------------

- Spring Boot
- Spring Kafka
- Zookeeper
- Kafka
- Apache httpd
- Postgres
- Docker

How To Run
----------

See [How to run](HOW-TO-RUN.md) for details.

Once you perform a create, update, delete operation on an employee, the audit info should be available from the audit service.

Remarks on the Code
-------------------

The microservices are: 
- [wfm-employee](wfm/wfm-employee) 
- [wfm-audit](wfm/wfm-audit)

The following components are in a Docker containter: Employee service, Audit service, Apache httpd, Kafka, Zookeeper and Postgres.

Incoming http request are handled by the Apache httpd server. It is
available at port 8080 of the Docker host
e.g. <http://localhost:8080>.  HTTP requests are forwarded to the
microservices. Kafka is used for the communication between the
microservices. Kafka needs Zookeeper to coordinate instances. Postgres
is used by all microservices to store data. Each microservices uses
its own database in the Postgres instance so they are decoupled in
that regard.

You can scale out the audit with e.g. `docker-compose scale audit=2`. 
The logs (`docker logs auditEmployee_audit_1`) will show which partitions the instances listen to and which records they handle.

You can also start a shell on the Kafka server `docker exec -it
auditEmployee_kafka_1 /bin/sh` and then take a look at the records in the
topic using `kafka-console-consumer.sh --bootstrap-server kafka:9092
--topic order --from-beginning`.
