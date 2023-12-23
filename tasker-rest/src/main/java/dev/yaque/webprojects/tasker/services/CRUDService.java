/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.yaque.webprojects.tasker.services;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author yaque
 */
public abstract interface  CRUDService<T, ID>{
    
    public Optional<T> findById(ID id);
    public Iterable<T> findAll();
    public <S extends T> S save(S entity);
    public <S extends T> S update(S entity);
    public void deleteById(ID id);
    CrudRepository<T, ID> getRepository();
}
