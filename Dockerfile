FROM eclipse-temurin:21-jdk AS base
WORKDIR /app
COPY build/libs/paymentService-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]