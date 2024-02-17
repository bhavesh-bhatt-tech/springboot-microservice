FROM eclipse-temurin:17.0.4.1_1-jre
EXPOSE 8082
ADD target/employee-service-0.0.1-SNAPSHOT.jar employee-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "employee-service-0.0.1-SNAPSHOT.jar"]