FROM tomcat:8.5.50-jdk8-openjdk

ARG WARFILE
ARG PATH

COPY ${WARFILE} /usr/local/tomcat/webapps/${PATH}.war