FROM openjdk:17-alpine
ARG WORKSPACE
COPY /$WORKSPACE InfoDisplay.jar
ENTRYPOINT ["java","-jar","/InfoDisplay.jar"]
