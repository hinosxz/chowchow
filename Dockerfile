FROM openjdk:latest
EXPOSE 8080

VOLUME /tmp
ADD /target/chowchow-0.0.1-SNAPSHOT.jar app.jar

CMD ["java", "-jar", "/app.jar"]
