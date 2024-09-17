# Use OpenJDK 17 as the base image
FROM openjdk:17-jdk-slim

# Copy the JAR file from the build folder to the container's root directory
COPY build/libs/ChatApp-0.0.1-SNAPSHOT.jar ChatApp-0.0.1-SNAPSHOT.jar

# Step 1: Install tzdata for timezone configuration
RUN apt-get update && apt-get install -y tzdata

# Step 2: Set the timezone
ENV TZ=Asia/Kolkata
# Expose port 8080 for the application
EXPOSE 8080

# Run the jar file when the container starts
ENTRYPOINT ["java", "-jar", "/ChatApp-0.0.1-SNAPSHOT.jar"]
