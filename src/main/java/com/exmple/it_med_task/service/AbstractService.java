package com.exmple.it_med_task.service;

import com.exmple.it_med_task.exceptions.ResourceNotFoundException;
import com.exmple.it_med_task.mappers.GenericMapper;
import com.exmple.it_med_task.models.GenericResponse;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/*
 *   This abstract service can be used by any services for basic CRUD operation and it will reduce the time , if we want to modify them no problemt @Override it and write your custom logic
 */
public abstract class AbstractService<ENTITY, MODEL, MAPPER extends GenericMapper<ENTITY,MODEL> , R extends JpaRepository<ENTITY, Long>> {
    protected final MAPPER mapper;
    protected final R repository;

    public AbstractService(MAPPER mapper, R repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Transactional
    public MODEL save(MODEL model) {
        ENTITY entity = mapper.toEntity(model);
        ENTITY saved = repository.save(entity);
        return mapper.toModel(saved);
    }

    @Transactional
    public MODEL update(MODEL model, Long id) {
        ENTITY entity = findById(id);
        mapper.updateEntityFromModel(entity, model);
        ENTITY savedEntity = repository.save(entity);
        return mapper.toModel(savedEntity);
    }

    @Transactional
    public GenericResponse delete(Long id) {
        boolean existsById = repository.existsById(id);
        if (existsById) {
            repository.deleteById(id);
            return GenericResponse.builder().status(HttpStatus.NO_CONTENT).success(true).message(String.format("successfully deleted! id: %s",id)).build();
        }else return GenericResponse.builder().status(HttpStatus.CONFLICT).success(false).message(String.format("successfully deleted! id: %s",id)).build();
    }

    public List<MODEL> findPageWithCriteria(Integer size, Integer page, MODEL model) {
        ENTITY appointment = mapper.toEntity(model);
        Pageable pageable = PageRequest.of(page,size);
        if (!Objects.isNull(appointment)){
            Example<ENTITY> example = Example.of(appointment);
            List<ENTITY> content = repository.findAll(example, pageable).getContent();
            return mapper.toModelList(content);
        }else {
            List<ENTITY> content = repository.findAll(pageable).getContent();
            return mapper.toModelList(content);
        }
    }

    public List<MODEL> findPage(Integer size, Integer page){
        return findPageWithCriteria(size,page, null);
    }

    public ENTITY findById(Long id) {
        return  repository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("not found with given id: %s", id)));

    }

}
