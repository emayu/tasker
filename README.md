# tasker
This project for managing simple tasks, it has a Rest API for handle tasks and a Angular fronted project

Steps to run this project:

1. Clone this Git repository
2. Build the application with `mvnw clean package spring-boot:run -pl tasker-rest`
3. Wait until the server launched successfully and visit `http://localhost:8080/tasker/v1/task`
4. Play with the Rest API

Important: Minimun version of java 17

## Status

- [x] REST Api implemented
- [x] Postman runner passed
- [ ] Front implemented
- [ ] Wrap on docker
  - [ ] Configure mysql db
  - [ ] create images with maven




## RESTful API and Testing

The API design and testing to the Tasker API is located in [postman/Tasker_Project.postman_collection.json](https://github.com/emayu/tasker/web/blob/master/postman/Tasker_Project.postman_collection.json), in the collection project you can view some examples and use it to perform end to end testing to the api

### Resources 
The principal resources are task and user

- GET http://localhost:8080/tasker/v1/task 
- GET http://localhost:8080/tasker/v1/user 

### Operations to task
- POST http://localhost:8080/tasker/v1/task/1/assigne/1 
- POST http://localhost:8080/tasker/v1/task/1/start
- POST http://localhost:8080/tasker/v1/task/1/done
- POST http://localhost:8080/tasker/v1/task/1/pending
