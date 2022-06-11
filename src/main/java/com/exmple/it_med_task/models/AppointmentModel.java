package com.exmple.it_med_task.models;


import com.exmple.it_med_task.entites.Identifier;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Pattern;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class AppointmentModel {

    private Long id;

    @JsonProperty(value = "identifier")
    private IdentifierModel identifier;

    @JsonProperty(value = "patient")
    @Pattern(regexp = "[1-9][0-9]{9}", message = "given patient data is not valid for our requirements")
    private String patient;

    @JsonProperty(value = "practitioner")
    @Pattern(regexp = "[1-9][0-9]{9}", message = "given practitioner data is not valid for our requirements")
    private String practitoner;

    @Pattern(regexp = "[1-9][0-9]{9}", message = "given organization data is not valid for our requirements")
    @JsonProperty(value = "organization")
    private String organization;
}
