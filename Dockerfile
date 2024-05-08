FROM maven:3.8.3-openjdk-17 AS build
COPY src/main/resources/application.properties application.properties
ADD target/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]