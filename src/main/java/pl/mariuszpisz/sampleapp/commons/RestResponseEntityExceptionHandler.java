package pl.mariuszpisz.sampleapp.commons;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.mariuszpisz.sampleapp.commons.exceptions.ResourceAlreadyExistException;
import pl.mariuszpisz.sampleapp.commons.exceptions.ResourceNotFoundException;
import pl.mariuszpisz.sampleapp.commons.models.ExceptionResponse;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@ControllerAdvice
public class RestResponseEntityExceptionHandler
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {
            ResourceAlreadyExistException.class
    })
    protected ResponseEntity<Object> handleBadRequest(RuntimeException ex) {
        log.warn("Exception: {} thrown with message: {}",
                ex.getClass(), ex.getMessage());

        return buildResponseEntity(new ExceptionResponse(BAD_REQUEST, ex.getMessage()));
    }

    @ExceptionHandler(value = {
            ResourceNotFoundException.class
    })
    protected ResponseEntity<Object> handleNotFound(RuntimeException ex) {
        log.warn("Exception: {} thrown with message: {}",
                ex.getClass(), ex.getMessage());

        return buildResponseEntity(new ExceptionResponse(NOT_FOUND, ex.getMessage()));
    }

    private ResponseEntity<Object> buildResponseEntity(ExceptionResponse exceptionResponse) {
        return new ResponseEntity<>(exceptionResponse, exceptionResponse.getStatus());
    }

}
