FROM frolvlad/alpine-java:jdk8-slim

MAINTAINER yisasthemanuel@gmail.com

#Variables de entorno
ENV EUREKA_URI http://localhost:8761/eureka

ARG JAR_FILE

ADD ${JAR_FILE} /app.jar 

EXPOSE 8080

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]