FROM openjdk:17-jdk-slim
LABEL authors="BieggerM"
# Set the working directory in the container
WORKDIR /app

# Copy the project’s jar file into the container at /app
COPY target/feed_service-0.0.1-SNAPSHOT.jar /app/feedservice.jar

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "/app/feedservice.jar"]