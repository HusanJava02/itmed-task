FROM openjdk:11
VOLUME /tmp
ARG JAR_FILE=target/it_med_task-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]