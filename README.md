# tasker
This project for managing simple tasks, it has a Rest API for handle tasks and a Angular fronted project


## RESTful API and Testing

The API desing and testing to the Tasker API is located in [postman/Tasker_Project.postman_collection.json](https://github.com/emayu/tasker/web/blob/master/postman/Tasker_Project.postman_collection.json), in the collection project you can view some examples and use it to perform end to end testing to the api

### Resources 
The principal resources are task and user

- GET http://localhost:8080/tasker/v1/task 
- GET http://localhost:8080/tasker/v1/user 

### Operations to task
- POST http://localhost:8080/tasker/v1/task/1/assigne/1 
- POST http://localhost:8080/tasker/v1/task/1/start
- POST http://localhost:8080/tasker/v1/task/1/done
- POST http://localhost:8080/tasker/v1/task/1/pending
