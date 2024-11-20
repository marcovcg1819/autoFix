FROM openjdk:17.0.1
ARG JAR_FILE=target/ms-vehiculos-0.0.1-SNAPSHOT.jar
ARG JAR_FILE
COPY ${JAR_FILE} vehiculos.jar
ENTRYPOINT ["java","-jar","vehiculos.jar"]