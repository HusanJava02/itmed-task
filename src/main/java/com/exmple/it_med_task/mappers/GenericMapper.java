package com.exmple.it_med_task.mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

public interface GenericMapper<E, M> {
    E toEntity(M model);

    M toModel(E entity);

    List<M> toModelList(List<E> entities);

    List<E> toEntityList(List<M> models);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromModel(@MappingTarget E entity, M model);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateModelFromEntity(E entity,@MappingTarget M model);

}
