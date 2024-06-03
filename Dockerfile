FROM amazoncorretto:17-alpine-jdk

COPY target/communities-microservice-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]
