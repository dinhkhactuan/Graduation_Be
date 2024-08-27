FROM maven:3.8.3-openjdk-19 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:19-jdk
WORKDIR /app
COPY --from=build /app/target/Graduation-Be.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]