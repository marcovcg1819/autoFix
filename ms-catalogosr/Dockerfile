FROM openjdk:17.0.1
ARG JAR_FILE=target/ms-catalogosr-0.0.1-SNAPSHOT.jar
ARG JAR_FILE
COPY ${JAR_FILE} catalogosr.jar
ENTRYPOINT ["java","-jar","catalogosr.jar"]