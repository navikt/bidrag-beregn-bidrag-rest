FROM navikt/java:14
LABEL maintainer="Team Bidrag" \
      email="bidrag@nav.no"

COPY ./target/bidrag-beregn-bidrag-rest-*.jar app.jar

EXPOSE 8080