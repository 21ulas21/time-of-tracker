FROM maven:3.8.3-openjdk-17 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

FROM openjdk:17-jdk
WORKDIR /app
COPY target/time-of-tracker-0.0.1-SNAPSHOT.jar myapp.jar
CMD ["java", "-jar", "myapp.jar"]
EXPOSE 8080