# Use OpenJDK 17 as the base image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the source code to the container
COPY . .

# Install tzdata for timezone configuration
RUN apt-get update && apt-get install -y tzdata

# Set the timezone
ENV TZ=Asia/Kolkata

# Build the JAR file using Gradle
RUN ./gradlew build

# Expose port 8080 for the application
EXPOSE 8080

# Run the JAR file from the build/libs directory
ENTRYPOINT ["java", "-jar", "/app/build/libs/ChatApp-0.0.1-SNAPSHOT.jar"]
