FROM openjdk:17-alpine
COPY ${{ github.workspace }}/target/InfoDisplay.jar InfoDisplay.jar
ENTRYPOINT ["java","-jar","/InfoDisplay.jar"]
