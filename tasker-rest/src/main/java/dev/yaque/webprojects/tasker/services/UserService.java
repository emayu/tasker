/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.yaque.webprojects.tasker.services;

import dev.yaque.webprojects.tasker.model.User;
import dev.yaque.webprojects.tasker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

/**
 *
 * @author yaque
 */
@Service
public class UserService extends ServiceFacade<User, Long>{
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public CrudRepository<User, Long> getRepository() {
        return userRepository;
    }
    
    
    
}
