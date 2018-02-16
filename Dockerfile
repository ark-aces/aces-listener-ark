FROM openjdk:8u131-jdk-alpine
VOLUME /tmp
WORKDIR /app
COPY ./target/aces_listener_ark-*.jar application.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/application.jar"]
