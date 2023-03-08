FROM openjdk:17-jdk-slim-buster
WORKDIR /app

COPY ./target/hookah-warehouse-rest-core-1.0-SNAPSHOT.jar app.jar

CMD ["java", "-jar", "app.jar"]

