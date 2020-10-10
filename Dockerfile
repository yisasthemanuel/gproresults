FROM adoptopenjdk/openjdk11-openj9:alpine-jre

MAINTAINER yisasthemanuel@gmail.com

RUN apk --update add \
    fontconfig \
    ttf-dejavu 

#Variables de entorno
ENV EUREKA_URI http://localhost:8761/eureka

ARG JAR_FILE

ADD ${JAR_FILE} /app.jar 

EXPOSE 8080

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]