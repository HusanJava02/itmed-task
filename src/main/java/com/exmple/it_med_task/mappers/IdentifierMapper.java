package com.exmple.it_med_task.mappers;

import com.exmple.it_med_task.entites.Identifier;
import com.exmple.it_med_task.models.IdentifierModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IdentifierMapper extends GenericMapper<Identifier, IdentifierModel> {
}
