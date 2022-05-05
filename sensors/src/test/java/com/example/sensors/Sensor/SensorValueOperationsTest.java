package com.example.sensors.Sensor;

import com.example.sensors.Exceptions.InvalidSensorOperationException;
import com.example.sensors.Exceptions.InvalidSensorValueException;
import com.example.sensors.POJOs.SensorChange;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SensorValueOperationsTest {
    @Test
    public void testMathOperations()
    {
        assertTrue(SensorValueOperations.performOperation(
                10,
                new SensorChange("set", 15)) == 15);
        assertTrue(SensorValueOperations.performOperation(
                10,
                new SensorChange("increment", 2)) == 12);
        assertTrue(SensorValueOperations.performOperation(
                10,
                new SensorChange("decrement", 2)) == 8);
    }

    @Test
    public void testInvalidOperationException()
    {
        InvalidSensorOperationException thrownException = assertThrows(
                InvalidSensorOperationException.class, () ->
                    SensorValueOperations.performOperation(
                                10,
                                new SensorChange("divide", 2)
                        ),
        "Expected InvalidSensorOperationException to be thrown, because" +
                "operation is not supported yet."
        );
    }
}
