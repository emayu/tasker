/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.yaque.webprojects.tasker.services;

import dev.yaque.webprojects.tasker.model.Task;
import dev.yaque.webprojects.tasker.model.User;
import dev.yaque.webprojects.tasker.repository.TaskRepository;
import dev.yaque.webprojects.tasker.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

/**
 *
 * @author yaque
 */
@Service
public class TaskService extends ServiceFacade<Task, Long> {
    
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepositoy;

    @Override
    public CrudRepository getRepository() {
        return taskRepository;
    }

    @Override
    public <S extends Task> S save(S task) {
        //initiate values by default
        task.setStatus(Task.Status.PENDING);
        task.setPlacedAt(LocalDateTime.now());
        return taskRepository.save(task);
    }
    
    public List<Task> findAll(String userId) throws NumberFormatException{
        if ("none".equals(userId)) {
            return taskRepository.findByAssignedToIsNull();
        }else if (userId != null){
            try {
                Optional<User> o = userRepositoy.findById(Long.valueOf(userId));
                if (o.isPresent()) {
                    return taskRepository.findByAssignedTo(o.get());
                } else {
                    return java.util.Collections.EMPTY_LIST;
                }
            } catch (NumberFormatException ex) {
                return java.util.Collections.EMPTY_LIST;
            }
        }
        return StreamSupport
                    .stream(taskRepository.findAll().spliterator(), true)
                    .toList();
            
    }
    
    
    public Task startTask(Long taskId) throws EntityNotFoundException{
        Optional<Task> o = taskRepository.findById(taskId);
        if(o.isPresent()){
            //Logic to put in progres a task
            Task t = o.get();
            t.setStatus(Task.Status.IN_PROGRESS);
            return taskRepository.save(t);
        }
        throw new EntityNotFoundException(String.format("TaskId %d Not found", taskId));
    }
    
    public Task doneTask(Long taskId) throws EntityNotFoundException{
        Optional<Task> o = taskRepository.findById(taskId);
        if(o.isPresent()){
            //Logic to put in done a task
            Task t = o.get();
            t.setStatus(Task.Status.DONE);
            return taskRepository.save(t);
        }
        throw new EntityNotFoundException(String.format("TaskId %d Not found", taskId));
    }
    public Task pedingTask(Long taskId) throws EntityNotFoundException{
        Optional<Task> o = taskRepository.findById(taskId);
        if(o.isPresent()){
            //Logic to put pending a task
            Task t = o.get();
            t.setStatus(Task.Status.PENDING);
            return taskRepository.save(t);
        }
        throw new EntityNotFoundException(String.format("TaskId %d Not found", taskId));
    }
    
    public Task assignUser(Long taskId, Long userId) throws EntityNotFoundException{
        Optional<Task> oT = taskRepository.findById(taskId);
        Optional<User> oU = userRepositoy.findById(userId);
        if(oT.isEmpty()){
            throw new EntityNotFoundException(String.format("TaskId %d Not found", taskId));
        }
        if(oU.isEmpty()){
            throw new EntityNotFoundException(String.format("userId %d Not found", taskId));
        }
        
        //Logic to assign a uset to a task
        Task t = oT.get();
        t.setAssignedTo(oU.get());
        return taskRepository.save(t);
        
    }
    
    
    
}
