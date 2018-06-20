FROM maven:3-alpine

COPY pom.xml capstone/

COPY src/ capstone/src/

WORKDIR capstone/

RUN mvn clean install -Dmaven.test.skip=true -DskipTests=false

EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "/capstone/target/capstoneproject-0.0.1-SNAPSHOT.war"]