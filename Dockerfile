FROM openjdk:11.0.11-jre

VOLUME /tmp

ENV DATABASE_HOST=HOST
ENV DATABASE_PORT=PORT
ENV DATABASE_USERNAME=USERNAME
ENV DATABASE_PASSWORD=PASSWORD

ADD target/Paydi_MMS_Services-0.0.1-SNAPSHOT.jar app-run.jar

ENTRYPOINT exec java -jar app-run.jar