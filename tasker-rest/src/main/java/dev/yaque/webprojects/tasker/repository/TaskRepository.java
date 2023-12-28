/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dev.yaque.webprojects.tasker.repository;

import dev.yaque.webprojects.tasker.model.Task;
import dev.yaque.webprojects.tasker.model.User;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author yaque
 */

public interface TaskRepository extends CrudRepository<Task, Long> {
    public List<Task> findByAssignedTo(User assignedTo);
    public List<Task> findByAssignedToIsNull();
}
