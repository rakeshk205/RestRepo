# Use OpenJDK 17 slim image
FROM openjdk:17-jdk-slim

# Copy jar into container
COPY target/RestService-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8080 (Spring Boot default)
EXPOSE 8080

# Run the jar
ENTRYPOINT ["java", "-jar", "/app.jar"]

