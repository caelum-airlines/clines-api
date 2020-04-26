FROM openjdk:11-jre-slim

ARG JAR

WORKDIR /clines

COPY ${JAR} /clines/app.jar

ENV JAVA_OPTS ''

ENV PORT '8080'

EXPOSE 8080

CMD java -XX:+UseContainerSupport $JAVA_OPTS -jar app.jar --server.port=$PORT