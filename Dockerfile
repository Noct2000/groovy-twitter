FROM gradle:8.3.0-jdk17 AS builder
WORKDIR /home/gradle/src
# Copy the source code into the image for building
COPY . /home/gradle/src
# Build
RUN ./gradlew build

# Run application
FROM openjdk:17-oracle
COPY --from=builder /home/gradle/src/build/libs/groovy-twitter-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
EXPOSE 5005
