FROM openjdk:19-jdk

COPY target/Graduation-Be.jar .

ENTRYPOINT ["java", "-jar","Graduation-Be.jar"]