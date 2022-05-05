package com.example.sensors.Exceptions;

/**
 * Exception class that is thrown when passed a negative value
 * to modify sensor's value.
 */
public class NegativeValuePassedException extends RuntimeException{
    public NegativeValuePassedException(){
        super("You cannot pass negative value when using operation" +
                " increment or decrement.");
    }
}
