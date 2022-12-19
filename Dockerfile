# Start with a base image containing Java runtime
FROM openjdk:11.0.10-jdk

# Add the application's jar to the container
ADD target/service-cloudbinder-example-*.jar service-cloudbinder-example.jar

# Run the jar file
ENTRYPOINT ["/bin/bash", "-c", "java $JAVA_OPTS -jar service-cloudbinder-example.jar"]
