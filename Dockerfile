FROM navikt/java:15
LABEL maintainer="Team Bidrag" \
      email="bidrag@nav.no"

COPY ./target/bidrag-beregn-barnebidrag-rest-*.jar app.jar

EXPOSE 8080
