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
- Zookeeper
- Kafka
- Postgres
- Docker

How To Run
----------

See [How to run](HOW-TO-RUN.md) for details.

Once you perform a create, update, delete operation on an employee, the
audit info should be available from the audit service.

Remarks on the Code
-------------------

The microservices are: 
- [wfm-employee]    <http://localhost:8080/employee>.
- [wfm-audit]       <http://localhost:9090/audit>.

The following components are in a Docker containter: Employee service,
Audit service, Kafka, Zookeeper and Postgres.

Incoming http request are handled by the internal application servers
in each micro service. The Employee service is available at port 8080
and the Audit service is available at port 9090. Kafka and Zookeeper is
used for the communication between the microservices. Kafka needs
Zookeeper to coordinate instances. Postgres is used by all microservices
to store data. Each microservices uses its own database in the Postgres
instance.

To troubleshoot the Kafka server open a shell by `docker exec -it
wfm_kafka_1 /bin/sh` and then take a look at the records in the
topic using `kafka-console-consumer.sh --bootstrap-server kafka:9092
--topic audit --from-beginning`.

To troubleshoot the Postgres server open a shell by `docker exec -it
wfm_postgres_1 /bin/sh` and then enter the psql console app with:
 `psql -U dbuser dbaudit` or `psql -U dbuser dbemployee`.
