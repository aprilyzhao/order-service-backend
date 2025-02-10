FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/order-service-backend.jar order-service-backend.jar
EXPOSE 8080
CMD ["java","-jar","order-service-backend.jar"]