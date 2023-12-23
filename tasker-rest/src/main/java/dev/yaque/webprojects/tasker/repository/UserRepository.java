/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dev.yaque.webprojects.tasker.repository;

import dev.yaque.webprojects.tasker.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author yaque
 */

public interface UserRepository extends CrudRepository<User, Long> {
    
}
