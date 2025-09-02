FROM eclipse-temurin:17.0.4.1_1-jre
EXPOSE 8088
ADD target/config-server-0.0.1.jar config-server-0.0.1.jar
ENTRYPOINT ["java", "-jar", "config-server-0.0.1.jar"]