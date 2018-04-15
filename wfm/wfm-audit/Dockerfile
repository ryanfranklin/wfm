FROM ewolff/docker-java
COPY target/wfm-audit-0.0.1-SNAPSHOT.jar .
CMD /usr/bin/java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5006 -Xmx400m -Xms400m -jar wfm-audit-0.0.1-SNAPSHOT.jar
EXPOSE 9090
