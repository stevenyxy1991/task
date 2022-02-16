# syntax=docker/dockerfile:1

# First stage: complete build environment
FROM maven:3.5.0-jdk-8-alpine AS builder
WORKDIR /app
COPY . /app
# add pom.xml and source code
ADD ./pom.xml pom.xml
ADD ./src src/
# package jar
#COPY settings.xml /usr/share/maven/conf/
RUN mvn package -Dmaven.test.skip=true

# Second stage: minimal runtime environment

FROM openjdk:8-jdk
# Change TimeZone
# RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone
# LABEL maintainer="bydongxing@gmail.com"
# copy jar from the first stage
COPY --from=builder /app/target/tast-0.0.1-SNAPSHOT.jar /app.jar
ENV JAVA_OPTS=""
ENV PARAMS=""
# run jar
ENTRYPOINT [ "sh", "-c", "java -Djava.security.egd=file:/dev/./urandom $JAVA_OPTS -jar /app.jar $PARAMS" ]


# FROM java:8
# EXPOSE 8081
# VOLUME /tmp
# ADD tast-0.0.1-SNAPSHOT.jar app.jar
# RUN sh -c 'touch /app.jar'
# ENV JAVA_OPTS=""
# ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
