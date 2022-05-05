package com.example.sensors.Exceptions;

/**
 * Custom exception that is being thrown then there is requested
 * an operation on Sensor's value, that's not supported by the API.
 */
public class InvalidSensorOperationException extends RuntimeException{

    /**
     * Constructor containing the name of invalidOperation.
     * @param invalidOperation Name of the invalid operation.
     */
    public InvalidSensorOperationException(String invalidOperation) {
        super("Could not perform an " + invalidOperation + " operation.");
    }
}
