# Use a Java base image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy the Gradle wrapper and build files
COPY gradlew gradlew.bat /app/
COPY gradle /app/gradle
COPY build.gradle.kts settings.gradle.kts /app/

# Copy the source code (including resources)
COPY src /app/src

# Make the Gradle wrapper executable
RUN chmod +x ./gradlew

# Run the Gradle build to create a fat JAR
RUN ./gradlew clean shadowJar --no-daemon

# Set the command to run tests using Gradle
CMD ["./gradlew", "test"]
