package com.exmple.it_med_task.utils;

import com.exmple.it_med_task.entites.Appointment;
import com.exmple.it_med_task.models.AppointmentModel;

public class AppointmentUtils {
    public static Appointment create() {

        Appointment appointment = new Appointment();
        appointment.setId(1L);
        appointment.setIdentifier(IdentifierUtil.create());
        appointment.setOrganization("organization");
        appointment.setPatient("patient");
        appointment.setPractitoner("practitioner");
        return appointment;

    }
}
