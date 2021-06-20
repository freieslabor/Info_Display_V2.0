# Info Display Version 2 Backend README

## Table of Contents

*  About Info Display
*  Installtion
*  Rest Interface

## About Info Display

The Info Display is a tool for all Hackerspace, Makerspaces or anything else.

With this tool you can show any people are your Space open or close. Or you can organize your Stuff in your Space, if you write all your stuff in a 
database and all memeber of the space can find stuff from the database. 

The Database for this application is a H2Database of your device, that you decided as your server for this application. This is a file which be created for the Application and this write into this file.


## Installtion

* Step One Please clone the application from [GitHub](https://github.com/freieslabor/Info_Display_V2.0)
* Step Two Create a file names `application.properties` in the folder `resources`. Exists no folder `resoucres` please create this in `src/main/`
* Step Three Write into `application.properties`this text:
```
spring.datasource.url=jdbc:h2:file:<<Place for the Database (Example: ~/Desktop/ >>InfoDisplayDB;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.data-username=InfoDisplay
spring.datasource.data-password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=update

server.port=8400
``` 
* Step Four Now you must use maven for compile the project, that generate the `.jar`.
* Step Five You can find the `.jar` file in the folder `target/`.
* Step Six 
