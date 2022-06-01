FROM openjdk:17-alpine
ARG WORKSPACE
COPY WORKSPACE/target/InfoDisplay.jar InfoDisplay.jar
ENTRYPOINT ["java","-jar","/InfoDisplay.jar"]
