FROM openjdk:latest

VOLUME /tmp
EXPOSE $PORT

RUN mkdir -p /app/
RUN mkdir -p /app/logs/

ADD target/chowchow-0.0.1-SNAPSHOT.jar /app/app.jar
CMD java -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=$SPRING_PROFILE -jar /app/app.jar