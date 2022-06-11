package com.exmple.it_med_task.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UniqueConstrainException extends RuntimeException{
    public UniqueConstrainException(String message) {
        super(message);
    }
}

