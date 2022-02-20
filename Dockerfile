FROM maven:3-openjdk-11-slim as build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

# FROM gcr.io/distroless/java11-debian11
FROM adoptopenjdk/openjdk11:alpine-jre
COPY --from=build /home/app/target/hiddenmuseum-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]