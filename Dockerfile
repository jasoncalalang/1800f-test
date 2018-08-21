FROM openjdk:8-jre
WORKDIR /usr/src/sample
COPY target/sample-0.0.1-SNAPSHOT.jar /usr/src/sample
EXPOSE 8080

#entry with exec
ENTRYPOINT exec java $JAVA_OPTS -jar sample-0.0.1-SNAPSHOT.jar --server.port=8080
