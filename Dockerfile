FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/order-service-backend-0.0.1-SNAPSHOT.jar /app/order-service-backend.jar
EXPOSE 8080
CMD ["java", "-jar", "/app/order-service-backend.jar"]