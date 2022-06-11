package com.exmple.it_med_task.utils;

import com.exmple.it_med_task.entites.Identifier;
import com.exmple.it_med_task.models.IdentifierModel;

public class IdentifierUtil {
    public static Identifier create() {
        Identifier identifier = new Identifier();
        identifier.setSystem("system");
        identifier.setValue("value");
        return identifier;
    }
}
