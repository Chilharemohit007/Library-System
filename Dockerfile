#FROM eclipse-temurin:21-jdk-alpine
#COPY "target/master-service-0.0.1-SNAPSHOT.jar" app.jar
#EXPOSE 9085
#ENTRYPOINT ["java", "-jar", "app.jar"]

FROM eclipse-temurin:21-jdk-alpine

# Copy the JAR file into the container
COPY target/LibrarySystem-0.0.1-SNAPSHOT.jar app.jar

# Expose port
EXPOSE 8089

# Run the JAR
ENTRYPOINT ["java","-jar","/app.jar"]

