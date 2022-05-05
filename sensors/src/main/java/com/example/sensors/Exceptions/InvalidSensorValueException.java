package com.example.sensors.Exceptions;

/**
 * Custom Exception that is being thrown when there is invalid sensor value being passed.
 * By invalid, it exceeds the limits (minimum or maximum value)
 */
public class InvalidSensorValueException extends RuntimeException{

    /**
     * Constructor containing information about the error - passed invalid Value and the range that
     * the value should be in.
     * @param setValue Invalid Value.
     * @param minValue Minimal Value.
     * @param maxValue Maximum Value.
     */
    public InvalidSensorValueException(Integer setValue, Integer minValue, Integer maxValue) {
        super("After performing an operation, value of the sensor would be " + setValue.toString() +
                " and it wouldn't be in the range of (" + minValue + ":" + maxValue + ")," +
                "so that operation cannot be performed.");
    }
}
