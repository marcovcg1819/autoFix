FROM openjdk:17.0.1
ARG JAR_FILE=target/ms-catalogosv-0.0.1-SNAPSHOT.jar
ARG JAR_FILE
COPY ${JAR_FILE} catalogosv.jar
ENTRYPOINT ["java","-jar","catalogosv.jar"]