FROM maven:3.8-jdk-8 AS build
COPY .. /app/
COPY ./tigris-client-1.0.0-beta.7-SNAPSHOT.jar /app/
COPY ./tigris-spring-boot-autoconfigure-1.0.0-beta.7-SNAPSHOT.jar /app/
COPY ./types-1.0.0-beta.7-SNAPSHOT.jar /app/
WORKDIR /app
RUN mvn org.apache.maven.plugins:maven-install-plugin:2.5.2:install-file -Dfile="tigris-client-1.0.0-beta.7-SNAPSHOT.jar"
RUN mvn org.apache.maven.plugins:maven-install-plugin:2.5.2:install-file -Dfile="tigris-spring-boot-autoconfigure-1.0.0-beta.7-SNAPSHOT.jar"
RUN mvn org.apache.maven.plugins:maven-install-plugin:2.5.2:install-file -Dfile="types-1.0.0-beta.7-SNAPSHOT.jar"
RUN mvn clean package

FROM gcr.io/distroless/java11-debian11
COPY --from=build /app//target/*-exec.jar /app/main.jar
WORKDIR /app
CMD ["main.jar"]
