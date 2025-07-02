# Use official Maven image with JDK 21 for build
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

# Copy project files
COPY pom.xml .
COPY src ./src

# Build the application (skip tests for faster build)
RUN mvn clean package -DskipTests

# Use a lightweight runtime image
FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

# Copy the JAR from the build stage
COPY --from=build /app/target/hometown-tracker-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8080 for Spring Boot
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
