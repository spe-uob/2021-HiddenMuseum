FROM maven:3-openjdk-11-slim as build
COPY src src
COPY pom.xml .
RUN mvn package

# FROM gcr.io/distroless/java11-debian11
FROM adoptopenjdk/openjdk11:alpine-jre
COPY --from=build /target/hiddenmuseum-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]