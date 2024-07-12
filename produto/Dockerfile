# Use a imagem oficial do Maven para construir nosso aplicativo Java
FROM maven:3.8.7-openjdk-18-slim AS build

WORKDIR /app

COPY . /app/

RUN mvn package

# Use a imagem oficial do OpenJDK para executar nosso aplicativo Java
FROM openjdk:11-jdk-slim

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8083

ENTRYPOINT ["java", "-jar", "app.jar"]