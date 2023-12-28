/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.yaque.webprojects.tasker.controllers;

import dev.yaque.webprojects.tasker.model.User;
import dev.yaque.webprojects.tasker.services.CRUDService;
import dev.yaque.webprojects.tasker.services.UserService;
import dev.yaque.webprojects.tasker.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 *
 * @author yaque
 */
@RestController
@RequestMapping(path = "/v1/user", produces = "application/json")
@CrossOrigin(origins="*")
public class UserServiceController extends ControllerFacade<User, Long>{

    private UserService userService;

    public UserServiceController(UserService userService) {
        this.userService = userService;
    }

    
    
    @GetMapping
    @Override
    public Iterable<User> getAll() {
        return super.getAll(); 
    }    
    
    @GetMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable("id") Long id){
        return super.get(id);
    }
    
    @PostMapping(consumes="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @Override
    public User post(@RequestBody User user){
        return super.post(user);
    }
    
    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<User> delete(@PathVariable("id") Long id){
        return super.delete(id);
    }
    
    @PutMapping(consumes="application/json")
    public ResponseEntity<User> update(@RequestBody User user){
        return super.put(user, user.getId());
    }
        
    
    @Override
    CRUDService<User, Long> getService() {
        return  userService;
    }
    
}
