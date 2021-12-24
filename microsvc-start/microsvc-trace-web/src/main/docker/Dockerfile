FROM java:alpine
VOLUME /tmp
ADD microsvc-trace-web-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
