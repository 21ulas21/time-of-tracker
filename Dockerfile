FROM openjdk:17-jdk
WORKDIR /app
COPY target/time-of-tracker-0.0.1-SNAPSHOT.jar myapp.jar
CMD ["java", "-jar", "myapp.jar"]
EXPOSE 8080