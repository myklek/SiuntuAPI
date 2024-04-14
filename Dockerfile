# Use the official maven image as the base image
FROM maven:3.8.3-openjdk-17 AS build

# Set the working directory in the image
WORKDIR /app

# Copy the pom.xml file into the image
COPY pom.xml .

# Download dependencies as specified in pom.xml
RUN mvn dependency:go-offline -B

# Copy the rest of the application code into the image
COPY . .

# Build the application
RUN mvn clean install

# Set the second stage of the build to use openjdk
FROM openjdk:17-jdk

# Copy the jar file from the build stage
COPY --from=build /app/target/*.jar app.jar

#ADD target/*.jar app.jar
# Copy the wait script into the image
# Set the command to run your application
ENTRYPOINT ["java", "-jar", "app.jar"]