# Build stage
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Run stage
FROM openjdk:17-jdk-slim
WORKDIR /app

# Install libfreetype6
RUN apt-get update && apt-get install -y libfreetype6 && rm -rf /var/lib/apt/lists/*

COPY --from=build /app/target/*.jar Graduation-Be.jar
ENTRYPOINT ["java","-jar","Graduation-Be.jar"]