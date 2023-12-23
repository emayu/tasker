/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.yaque.webprojects.tasker.controllers;

import dev.yaque.webprojects.tasker.model.Task;
import dev.yaque.webprojects.tasker.model.User;
import dev.yaque.webprojects.tasker.model.dto.TaskDTO;
import dev.yaque.webprojects.tasker.services.CRUDService;
import dev.yaque.webprojects.tasker.services.TaskService;
import dev.yaque.webprojects.tasker.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author yaque
 */
@RestController
@RequestMapping(path = "/v1/task", produces = "application/json")
public class TaskServiceController extends ControllerFacade<Task, Long> {
    
    private TaskService taskService;
    private UserService userService;

    public TaskServiceController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

   
    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    
    
    @GetMapping
    public List<TaskDTO> getAllDTO(@RequestParam(required = false) Long assignedTo) {
        
        return StreamSupport
                .stream(taskService.findAll(assignedTo).spliterator(), true)
                .map( task -> TaskDTO.toDTO(task))
                .collect(Collectors.toList());
    }    
    
    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getDTO(@PathVariable("id") Long id){
        Optional<Task> o = getService().findById(id);
        if(o.isPresent()){
            TaskDTO task = TaskDTO.toDTO(o.get());
            return new ResponseEntity<>(task, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
    
    @PostMapping(consumes="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDTO post(@RequestBody TaskDTO task){
        final Task entity = toTask(task);
        Task createdEntity = super.post(entity);
        return TaskDTO.toDTO(createdEntity);
    }
    
    public Task toTask(TaskDTO dto){
        final Task entity = dto.toEntity();
        if(dto.getAssignedTo() != null){
            Optional<User> o = userService.findById(dto.getAssignedTo());
            o.ifPresent(u -> entity.setAssignedTo(u));
        }
        return entity;
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Task> delete(@PathVariable("id") Long id){
        return super.delete(id);
    }
    
    @PutMapping(consumes="application/json")
    public ResponseEntity<TaskDTO> update(@RequestBody TaskDTO task){
        final Task entity = toTask(task);
        Optional<Task> o = getService().findById(entity.getId());
        if(o.isPresent()){
            Task updated = getService().update(entity);
            return new ResponseEntity<>(TaskDTO.toDTO(updated), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
    
    @PostMapping("/{taskId}/start")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public TaskDTO startTask(@PathVariable("taskId") Long taskId, HttpServletResponse response){
        try{
            Task t = taskService.startTask(taskId);
            return TaskDTO.toDTO(t);
        }catch(EntityNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }
    
    @PostMapping("/{taskId}/done")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public TaskDTO doneTask(@PathVariable("taskId") Long taskId, HttpServletResponse response){
        try{
            Task t = taskService.doneTask(taskId);
            return TaskDTO.toDTO(t);
        }catch(EntityNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }
    
    @PostMapping("/{taskId}/pending")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public TaskDTO pendingTask(@PathVariable("taskId") Long taskId, HttpServletResponse response){
        try{
            Task t = taskService.pedingTask(taskId);
            return TaskDTO.toDTO(t);
        }catch(EntityNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }
    
    @PostMapping("/{taskId}/assigne/{userId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public TaskDTO assingUser(@PathVariable("taskId") Long taskId, @PathVariable("userId") Long userId, HttpServletResponse response){
        try{
            Task t = taskService.assignUser(taskId, userId);
            return TaskDTO.toDTO(t);
        }catch(EntityNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    
    @Override
    CRUDService<Task, Long> getService() {
        return taskService;
    }
}
