FROM jboss/wildfly

WORKDIR /opt/jboss/wildfly

ADD ./target/tour.war /opt/jboss/wildfly/standalone/deployments/
COPY ./resources/jboss.config/. /wildfly/standalone/configuration/

EXPOSE 9090
