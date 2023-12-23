#!/bin/sh

echo "********************************************************"
echo "Waiting for the database server to start "
echo "********************************************************"
while ! `nc -z mysql-db 3306`; do sleep 3; done
echo ">>>>>>>>>>>> Database Server has started"

echo "********************************************************"
echo "Starting Tasker Service with Profile :  $SPRING_PROFILES_ACTIVE";
echo "********************************************************"
java -jar /usr/local/taskerservice/tasker-rest-0.0.1.jar
