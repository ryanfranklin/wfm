FROM ewolff/docker-java
COPY target/wfm-employee-0.0.1-SNAPSHOT.jar .
CMD /usr/bin/java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005 -Xmx400m -Xms400m -jar wfm-employee-0.0.1-SNAPSHOT.jar
EXPOSE 8080
