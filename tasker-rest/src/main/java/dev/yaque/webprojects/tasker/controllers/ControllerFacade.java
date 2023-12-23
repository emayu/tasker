/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.yaque.webprojects.tasker.controllers;

import dev.yaque.webprojects.tasker.services.CRUDService;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author yaque
 */
public abstract class ControllerFacade<T, ID> {
    
    abstract CRUDService<T, ID> getService();
    
    public Iterable<T> getAll(){
        return getService().findAll();
    }
    
    public ResponseEntity<T> get(ID id){
        Optional<T> o = getService().findById(id);
        if(o.isPresent()){
            return new ResponseEntity<>(o.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
    
    public T post(T entity){
        return getService().save(entity);
    }
    
    public  ResponseEntity<T> put(T entity, ID id){
        Optional<T> o = getService().findById(id);
        if(o.isPresent()){
            T updated = getService().update(entity);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
    
    public ResponseEntity<T> delete(ID id){
        Optional<T> o = getService().findById(id);
        if(o.isPresent()){
            getService().deleteById(id);
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        
    }
    
}
