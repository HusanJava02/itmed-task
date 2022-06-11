package com.exmple.it_med_task.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Builder
@Getter
@Setter
public class GenericResponse {
    private Boolean success;
    private HttpStatus status;
    private String message;
    private Object error;
}
