package com.exmple.it_med_task.mappers;

import com.exmple.it_med_task.entites.Appointment;
import com.exmple.it_med_task.models.AppointmentModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {IdentifierMapper.class})
public interface AppointmentMapper extends GenericMapper<Appointment , AppointmentModel> {
}
