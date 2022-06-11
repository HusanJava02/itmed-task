package com.exmple.it_med_task.service;

import com.exmple.it_med_task.entites.Appointment;
import com.exmple.it_med_task.exceptions.ResourceNotFoundException;
import com.exmple.it_med_task.mappers.AppointmentMapper;
import com.exmple.it_med_task.models.AppointmentModel;
import com.exmple.it_med_task.repository.AppointmentRepository;
import com.exmple.it_med_task.utils.AppointModelUtils;
import com.exmple.it_med_task.utils.AppointmentUtils;
import org.hibernate.mapping.Any;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.verification.AtLeast;
import org.mockito.internal.verification.Times;
import org.mockito.verification.VerificationMode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AppointmentServiceTest {
    @InjectMocks
    private AppointmentService appointmentService;

    @Mock
    private AppointmentMapper appointmentMapper;

    @Mock
    private AppointmentRepository appointmentRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save_appointment_test() {
        AppointmentModel appointmentModel = AppointModelUtils.create();
        Appointment appointment = AppointmentUtils.create();
        when(appointmentRepository
                .save(any(Appointment.class)))
                .thenReturn(appointment);
        when(appointmentMapper
                .toEntity(any(AppointmentModel.class)))
                .thenReturn(appointment);
        when(appointmentMapper
                .toModel(any(Appointment.class)))
                .thenReturn(appointmentModel);
        AppointmentModel save = appointmentService
                .save(appointmentModel);
        assertNotNull(save);
        assertEquals(1, save.getId());
        verify(appointmentRepository, new Times(1))
                .existsByIdentifierSystemAndIdentifierValue(appointment
                                .getIdentifier()
                                .getSystem(),
                        appointment
                                .getIdentifier()
                                .getValue());
    }

    @Test
    void update_appointment_test() {
        AppointmentModel appointmentModel = AppointModelUtils.create();
        Appointment appointment = AppointmentUtils.create();
        when(appointmentRepository
                .save(any(Appointment.class)))
                .thenReturn(appointment);
        when(appointmentMapper
                .toEntity(any(AppointmentModel.class)))
                .thenReturn(appointment);
        when(appointmentMapper
                .toModel(any(Appointment.class)))
                .thenReturn(appointmentModel);
        AppointmentModel save = appointmentService
                .save(appointmentModel);
        when(appointmentRepository
                .findById(appointment
                        .getId()))
                .thenReturn(Optional.of(appointment));

        AppointmentModel updated = appointmentService.update(appointmentModel, appointmentModel.getId());
        assertNotNull(updated);
        assertEquals(1, updated.getId());
        verify(appointmentRepository, new AtLeast(1)).findById(appointment.getId());
    }


    @Test
    void findPageWithCriteria_test() {
        Appointment appointment = AppointmentUtils.create();
        AppointmentModel appointmentModel = AppointModelUtils.create();

        List<AppointmentModel> appointmentModels = List.of(appointmentModel, appointmentModel, appointmentModel);
        List<Appointment> appointments = List.of(appointment, appointment, appointment);
        Page<Appointment> appointmentsPage = new PageImpl<>(appointments);

        PageRequest pageable = PageRequest.of(0, 20);
        when(appointmentRepository.findAll(pageable))
                .thenReturn(appointmentsPage);

        when(appointmentMapper.toModelList(appointments))
                .thenReturn(appointmentModels);

        List<AppointmentModel> pageWithCriteria = appointmentService.findPageWithCriteria(20, 0, null);
        assertNotNull(pageWithCriteria);
        assertEquals(3, pageWithCriteria.size());
        verify(appointmentRepository, new AtLeast(1)).findAll(pageable);

    }

    @Test
    void findById_shouldThrowException_test() {
        when(appointmentRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> {
            appointmentService.findById(1L);
        });
    }

    @Test
    void findById_shouldReturnAppointment() {
        when(appointmentRepository.findById(anyLong())).thenReturn(Optional.of(AppointmentUtils.create()));
        assertDoesNotThrow(() -> {
            appointmentService.findById(1L);
        });
        assertNotNull(appointmentService.findById(1L));
    }


}