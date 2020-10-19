FROM maven:3.5.2-jdk-8-alpine AS builder
COPY pom.xml /tmp/
COPY src /tmp/src/
WORKDIR /tmp/
RUN mvn package

FROM openjdk:8-jdk-alpine
COPY --from=builder /tmp/target/*.jar /app/app.jar

#ARG PROFILE

# Ports exposing
EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/app.jar"]