FROM eclipse-temurin:17.0.4.1_1-jre
EXPOSE 8761
ADD target/service-registry-0.0.1-SNAPSHOT.jar service-registry-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "service-registry-0.0.1-SNAPSHOT.jar"]