package com.exmple.it_med_task.utils;

import com.exmple.it_med_task.models.AppointmentModel;
import com.exmple.it_med_task.models.IdentifierModel;

public class IdentifierModelUtil {
    public static IdentifierModel create() {
        IdentifierModel identifierModel = new IdentifierModel();
        identifierModel.setSystem("system");
        identifierModel.setValue("value");
        return identifierModel;
    }
}
