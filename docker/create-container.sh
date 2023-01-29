#!/bin/bash

while getopts autostart: flag
do
    case "${flag}" in
        autostart) DOCKER_CONATINER_AUTOSTART=${OPTARG};;
    esac
done

DIR_TARGET="../target/" 
RESULT_CONATINER=""

sudo rm -rf "${DIR_TARGET}"
sudo docker stop infodisplay
sudo docker rm infodisplay
sudo docker image rm infodisplay

if [ ! -d "${DIR_TARGET}" ]; then
    cd ../
    mvn clean install
    sudo docker build ./docker/ -t infodisplay 
    RESULT_IMAGE=$(sudo docker images -q infodisplay)
    if $DOCKER_CONATINER_AUTOSTART; then
            sudo docker run -d --name InfoDisplay -p 8400:8400 infodisplay
    else
            echo 'Docker ready for run!'
    fi
else
    echo 'Target directory can not remove'
fi