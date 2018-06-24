FROM maven:3-alpine

COPY pom.xml capstone/

COPY src/ capstone/src/

WORKDIR capstone/

EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "capstoneproject-0.0.1-SNAPSHOT.war"]