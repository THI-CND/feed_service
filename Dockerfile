FROM maven:3-eclipse-temurin-23 AS build
LABEL maintainer="marius biegger"

WORKDIR /build

COPY pom.xml .
COPY src src

RUN mvn clean install -DskipTests

FROM eclipse-temurin:23-alpine

WORKDIR /app

COPY --from=build /build/target/*.jar server.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "server.jar"]