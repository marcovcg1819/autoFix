FROM openjdk:17.0.1
ARG JAR_FILE=target/ms-reparaciones-0.0.1-SNAPSHOT.jar
ARG JAR_FILE
COPY ${JAR_FILE} reparaciones.jar
ENTRYPOINT ["java","-jar","reparaciones.jar"]