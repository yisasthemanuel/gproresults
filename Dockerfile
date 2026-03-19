FROM eclipse-temurin:21-jre-jammy

LABEL maintainer="yisasthemanuel@gmail.com"

# Instalar fuentes necesarias
RUN apt-get update && apt-get install -y \
    fontconfig \
    fonts-dejavu-core \
    && rm -rf /var/lib/apt/lists/*

ENV EUREKA_URI=http://localhost:8761/eureka

COPY target/*.jar /app.jar

EXPOSE 8080

ENTRYPOINT ["java","-XX:+UseContainerSupport","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]