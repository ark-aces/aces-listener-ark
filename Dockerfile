FROM openjdk:8u131-jdk-alpine
VOLUME /tmp
WORKDIR /app
COPY ./target/ark-java-client-0.1.0.jar .
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/ark-java-client-0.1.0.jar"]
