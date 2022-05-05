package com.example.sensors.ExceptionsHandlers;

import com.example.sensors.Exceptions.InvalidSensorValueException;
import com.example.sensors.Exceptions.NegativeValuePassedException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.constraints.Negative;

/**
 * Exception handler in case of NegativeValuePassedException.
 * Associated with the Sensor entity - when the passed value to set is negative.
 */
@ControllerAdvice
public class NegativeValuePassedAdvice extends ResponseEntityExceptionHandler {
    /**
     * Exception handler - simply returns the message of the exception to the end-user.
     * @param ex Exception
     * @return Exception's message
     */
    @ResponseBody
    @ExceptionHandler(NegativeValuePassedException.class)
    ResponseEntity<Object> invalidSensorValueHandler(NegativeValuePassedException ex,
                                                     WebRequest request){
        return handleExceptionInternal(ex, ex.getMessage(),
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
