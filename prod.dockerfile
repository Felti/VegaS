FROM maven:3.8.1-openjdk-17-slim as builder

WORKDIR /usr/app

COPY . ./

RUN mvn -DskipTests=true clean package

FROM openjdk:17-oracle

WORKDIR /usr/server

COPY --from=builder /usr/app/docker/vega.jar ./app.jar

VOLUME /usr/server/logs/

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "/usr/server/app.jar"]
