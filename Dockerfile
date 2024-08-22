FROM openjdk:17

COPY target/proposta-app-1.0.0-SNAPSHOT.jar proposta-app.jar

ENTRYPOINT ["java", "-jar", "proposta-app.jar"]