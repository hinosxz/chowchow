FROM openjdk:latest

VOLUME /tmp
# EXPOSE 8080

RUN mkdir -p /app/
RUN mkdir -p /app/logs/

ADD target/chowchow-0.0.1-SNAPSHOT.jar /app/app.jar
CMD ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=prod", "-jar", "/app/app.jar"]