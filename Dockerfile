# Build stage
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Run stage
FROM openjdk:17-jdk-slim
WORKDIR /app

#news
# Install required packages
RUN apt-get update && apt-get install -y \
    libfreetype6 \
    fontconfig \
    && rm -rf /var/lib/apt/lists/*

ENV JAVA_TOOL_OPTIONS="-Djava.awt.headless=true -Dsun.java2d.fontconfig=/app/fontconfig"

# Create a basic font configuration file
RUN echo '<?xml version="1.0"?>\
<!DOCTYPE fontconfig SYSTEM "fonts.dtd">\
<fontconfig>\
    <dir>/usr/share/fonts</dir>\
    <cachedir>/app/fontconfig</cachedir>\
</fontconfig>' > /etc/fonts/fonts.conf \

# Create font cache directory and run fc-cache
RUN mkdir -p /app/fontconfig && fc-cache -v

COPY --from=build /app/target/*.jar Graduation-Be.jar
ENTRYPOINT ["java","-jar","Graduation-Be.jar"]