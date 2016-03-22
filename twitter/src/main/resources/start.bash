#!/bin/bash
clear
echo "------------ Starting Script ------------"
echo "........................"
echo "........"
FILE_DIR="/media/jackalhan/USERDATA/Development/Java/IntelliJ_Workspaces/ArtificalIntelligence/emoweat/twitter/target/"
FILE_NAME="uber-emoweat-twitter-1.0-SNAPSHOT.jar"
CONFIG_DIR="/media/jackalhan/USERDATA/Development/Java/conf/"
LOGFILE_DIR="/media/jackalhan/USERDATA/Development/Java/conf/emoweat/logs/"
NOW=$(date +"%F")
LOGFILE_NAME=$LOGFILE_DIR ${NOW} ".log"

$ java -jar -Dconfigdir=${CONFIG_DIR} ${FILE_DIR} ${FILE_OUTPUT} > ${LOGFILE_NAME}