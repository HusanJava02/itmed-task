package com.exmple.it_med_task.advices;

import com.exmple.it_med_task.exceptions.ResourceNotFoundException;
import com.exmple.it_med_task.exceptions.UniqueConstrainException;
import com.exmple.it_med_task.models.GenericResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
@Log4j2
public class ExceptionHandlerAdvice {

    @ExceptionHandler({MethodArgumentNotValidException.class, ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public GenericResponse validationException(MethodArgumentNotValidException exception) {
        log.error(exception);
        return GenericResponse.builder().message(String.format("%s, Requested bad properties",exception.getMessage())).success(false).status(HttpStatus.BAD_REQUEST).build();
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public GenericResponse dataNotFoundException(ResourceNotFoundException exception){
        log.error(exception);
        return GenericResponse.builder().message(String.format("%s : Record not found with given id",exception.getMessage())).status(HttpStatus.NOT_FOUND).success(true).build();
    }

    @ExceptionHandler({UniqueConstrainException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public GenericResponse uniqueException(UniqueConstrainException exception) {
        log.error(exception);
        return GenericResponse.builder().status(HttpStatus.CONFLICT).success(false).message(exception.getMessage()).build();
    }

}
