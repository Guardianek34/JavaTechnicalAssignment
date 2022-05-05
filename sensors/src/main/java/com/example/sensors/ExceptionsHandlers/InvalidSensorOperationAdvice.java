package com.example.sensors.ExceptionsHandlers;


import com.example.sensors.Exceptions.InvalidSensorOperationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Exception handler in case of InvalidSensorOperationAdvice.
 * Associated with the Sensor entity - when the passed operation is not being
 * currently supported.
 */
@ControllerAdvice
public class InvalidSensorOperationAdvice extends ResponseEntityExceptionHandler {

    /**
     * Exception handler - simply returns the message of the exception to the end-user.
     * @param ex Exception
     * @return Exception's message
     */
    @ResponseBody
    @ExceptionHandler(InvalidSensorOperationException.class)
    ResponseEntity<Object> invalidSensorOperationHandler(InvalidSensorOperationException ex,
                                                         WebRequest request){

        return handleExceptionInternal(ex, ex.getMessage(),
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

}
