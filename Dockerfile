# syntax=docker/dockerfile:1

FROM eclipse-temurin:21-alpine

LABEL maintainer="github.com/evandrosouza89"

EXPOSE 8081

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./

RUN ./mvnw dependency:resolve

COPY src ./src

CMD ["./mvnw", "spring-boot:run"]