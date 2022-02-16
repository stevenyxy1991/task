# syntax=docker/dockerfile:1

# First stage: complete build environment
FROM maven:3.5.0-jdk-8-alpine AS builder
WORKDIR /app
COPY . /app
# add pom.xml and source code
ADD ./pom.xml pom.xml
ADD ./src src/
# package jar
RUN mvn package -Dmaven.test.skip=true

# Second stage: minimal runtime environment
FROM openjdk:8-jdk
# copy jar from the first stage
COPY --from=builder /app/target/task-0.0.1-SNAPSHOT.jar /app.jar
ENV JAVA_OPTS=""
ENV PARAMS=""
# run jar
ENTRYPOINT [ "sh", "-c", "java -Djava.security.egd=file:/dev/./urandom $JAVA_OPTS -jar /app.jar $PARAMS" ]