/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.yaque.webprojects.tasker.model.dto;

import dev.yaque.webprojects.tasker.model.*;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author yaque
 */

public class TaskDTO {
    
    
    private Long Id;
    private String title;
    private String description;
    private LocalDateTime placedAt;
    private Task.Status status;
    private Long assignedTo;
    

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getPlacedAt() {
        return placedAt;
    }

    public void setPlacedAt(LocalDateTime placedAt) {
        this.placedAt = placedAt;
    }

    public Task.Status getStatus() {
        return status;
    }

    public void setStatus(Task.Status status) {
        this.status = status;
    }

    public Long getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(Long assignedTo) {
        this.assignedTo = assignedTo;
    }

    
    
    public static TaskDTO toDTO(Task t){
        TaskDTO dto = new TaskDTO();
        dto.setId(t.getId());
        dto.setAssignedTo(t.getAssignedTo() == null ? null : t.getAssignedTo().getId());
        dto.setDescription(t.getDescription());
        dto.setPlacedAt(t.getPlacedAt());
        dto.setStatus(t.getStatus());
        dto.setTitle(t.getTitle());
        return dto;
    }
    
    public Task toEntity(){
        Task t = new Task();
        t.setDescription(getDescription());
        t.setId(getId());
        t.setPlacedAt(getPlacedAt());
        t.setStatus(getStatus());
        t.setTitle(getTitle());
        return t;
    }
    
}
