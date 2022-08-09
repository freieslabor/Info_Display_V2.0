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

* Please use the instructions of the installtion repository [click here](https://github.com/freieslabor/Info_Display_V2.0_Installtion)

## Rest interface

### Room status

Get request for current roomstatus:
> GET -> {Domain}/Roomstatus

Get request for a history of statute:
> GET -> {Domain}/Roomstatus/All

Post request for set a new status:
> POST -> {Domain}/Roomstatus?status=open or close

### Stuff in space

Get request for all stuff in space entries
> GET -> {Domain}/StuffInSpace/All

Get request for find an entry
> GET -> {Domain}//StuffInSpace/Find?name=[what you want to find]




