package com.exmple.it_med_task.service;

import com.exmple.it_med_task.entites.Appointment;
import com.exmple.it_med_task.exceptions.UniqueConstrainException;
import com.exmple.it_med_task.mappers.AppointmentMapper;
import com.exmple.it_med_task.models.AppointmentModel;
import com.exmple.it_med_task.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService extends AbstractService<Appointment, AppointmentModel, AppointmentMapper, AppointmentRepository>{
    public AppointmentService(AppointmentMapper mapper, AppointmentRepository repository) {
        super(mapper, repository);
    }

    @Override
    public AppointmentModel save(AppointmentModel appointmentModel) {
        Boolean existsByValueAndSystem = repository
                .existsByIdentifierSystemAndIdentifierValue(
                        appointmentModel
                        .getIdentifier()
                        .getSystem(),
                        appointmentModel.getIdentifier().getValue());
        if (!existsByValueAndSystem){
            return super.save(appointmentModel);
        }
        throw new UniqueConstrainException(String.format("Appointment identifier already exists: value: %s, system: %s",
                appointmentModel.getIdentifier().getValue(),
                appointmentModel.getIdentifier().getSystem()));
    }

    public AppointmentModel findAppointmentById(Long id) {
        return mapper.toModel(super.findById(id));
    }
}
