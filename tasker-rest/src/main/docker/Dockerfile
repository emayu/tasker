FROM openjdk:17-jdk-alpine
RUN  apk update && apk upgrade && apk add netcat-openbsd
RUN mkdir -p /usr/local/taskerservice
ADD tasker-rest-0.0.1.jar /usr/local/taskerservice/
ADD run.sh run.sh
RUN chmod +x run.sh
CMD ./run.sh