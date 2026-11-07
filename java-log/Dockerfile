FROM maven:3.9-amazoncorretto-21-alpine

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests

CMD ["java", "-jar", "target/java-log-1.0-SNAPSHOT.jar"]
