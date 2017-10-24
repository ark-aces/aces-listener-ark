FROM openjdk:8u131-jdk-alpine
VOLUME /tmp
WORKDIR /app
COPY ./target/aces-encoded-listener-ark-1.0.0.jar .
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/aces-encoded-listener-ark-1.0.0.jar"]
