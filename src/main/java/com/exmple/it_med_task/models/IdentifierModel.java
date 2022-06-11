package com.exmple.it_med_task.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class IdentifierModel {
    @JsonProperty(value = "system")
    private String system;

    @JsonProperty(value = "value")
    private String value;
}
