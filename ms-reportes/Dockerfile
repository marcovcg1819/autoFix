FROM openjdk:17.0.1
ARG JAR_FILE=target/cl.usachReportes-0.0.1-SNAPSHOT.jar
ARG JAR_FILE
COPY ${JAR_FILE} reportes.jar
ENTRYPOINT ["java","-jar","reportes.jar"]