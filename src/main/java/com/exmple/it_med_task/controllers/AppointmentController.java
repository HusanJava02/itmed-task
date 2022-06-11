package com.exmple.it_med_task.controllers;

import com.exmple.it_med_task.criterias.AppointmentCriteria;
import com.exmple.it_med_task.entites.Appointment;
import com.exmple.it_med_task.models.AppointmentModel;
import com.exmple.it_med_task.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AppointmentModel add(@RequestBody @Valid AppointmentModel appointmentModel) {
        return appointmentService.save(appointmentModel);
    }

    @GetMapping(value = "/{id}")
    public AppointmentModel getById(@PathVariable Long id) {
        return appointmentService.findAppointmentById(id);
    }

    @GetMapping("/list")
    public List<AppointmentModel> getPage(@RequestParam(defaultValue = "20") Integer size, @RequestParam(defaultValue = "0") Integer page) {
        return appointmentService.findPage(size,page);
    }

    @PutMapping
    public AppointmentModel edit(@RequestBody AppointmentModel appointmentModel) {
        return appointmentService.update(appointmentModel, appointmentModel.getId());
    }

    @PostMapping("/list")
    public List<AppointmentModel> getByCriteria(@RequestParam(defaultValue = "20") Integer size, @RequestParam(defaultValue = "0") Integer page, @RequestBody AppointmentModel appointmentModel) {
       return appointmentService.findPageWithCriteria(size, page,appointmentModel);
    }
}
