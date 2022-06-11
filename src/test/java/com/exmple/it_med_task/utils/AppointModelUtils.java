package com.exmple.it_med_task.utils;

import com.exmple.it_med_task.models.AppointmentModel;

public class AppointModelUtils {

    public static AppointmentModel create() {

        AppointmentModel appointmentModel = new AppointmentModel();
        appointmentModel.setId(1L);
        appointmentModel.setIdentifier(IdentifierModelUtil.create());
        appointmentModel.setOrganization("9999999999");
        appointmentModel.setPatient("9999999999");
        appointmentModel.setPractitoner("9999999999");
        return appointmentModel;

    }

}
