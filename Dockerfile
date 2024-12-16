FROM eclipse-temurin:17-jre

WORKDIR /app

COPY build/libs/tasks-manager-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
