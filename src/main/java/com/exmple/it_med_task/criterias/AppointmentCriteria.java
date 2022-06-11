package com.exmple.it_med_task.criterias;

import com.exmple.it_med_task.entites.Appointment;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.*;

public class AppointmentCriteria {

    public static Example<Appointment> example(Appointment appointment) {
        ExampleMatcher exampleMatcher = ExampleMatcher
                .matchingAll()
                .withMatcher("patient",  exact())
                .withMatcher("practitioner", exact())
                .withMatcher("organization", ignoreCase())
                .withMatcher("system_url", ignoreCase())
                .withMatcher("value", exact());
        return Example.of(appointment,exampleMatcher);
    }

}
