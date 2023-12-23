/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.yaque.webprojects.tasker.services;

import java.util.Optional;

/**
 *
 * @author yaque
 */
public abstract class ServiceFacade <T, ID> implements CRUDService<T,ID>{
    public Optional<T> findById(ID id){
        return getRepository().findById(id);
    }
    public Iterable<T> findAll(){
        return getRepository().findAll();
    }
    public <S extends T> S save(S entity){
        return getRepository().save(entity);
    }
    public <S extends T> S update(S entity){
        return getRepository().save(entity);
    }
    public void deleteById(ID id){
        getRepository().deleteById(id);
    }
}
