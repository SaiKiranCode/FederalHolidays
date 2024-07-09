FROM openjdk:11
VOLUME /tmp
WORKDIR /app
COPY target/holidays-0.0.1-SNAPSHOT.jar holidays.jar
ENTRYPOINT ["java","-jar","/holidays.jar"]
