package com.example.sensors.Sensor;

import com.example.sensors.Exceptions.InvalidSensorOperationException;
import com.example.sensors.Exceptions.InvalidSensorValueException;
import com.example.sensors.Exceptions.NegativeValuePassedException;
import com.example.sensors.POJOs.SensorChange;
import com.example.sensors.Parsers.URLParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SensorServiceTest {

    @Mock
    private SensorRepository sensorRepository;

    @InjectMocks
    private SensorService service;

    private  Sensor sensor = new Sensor(
            1L,
            "temperature",
            50,
            100,
            -50,
            null);


    /**
     * Test verifies whether sensorRepository.findAll() is being called
     * as intended.
     */
    @Test
    void verifyGetAllSensors() {
        service.getSensors();
        verify(sensorRepository).findAll();
    }

    /**
     * Test checks if setting value below minValue in sensor throws an exception.
     */
    @Test
    void verifyModifyValueExceedMinValue(){
        when(sensorRepository.findById(1L)).thenReturn(Optional.of(sensor));

        InvalidSensorValueException thrownException = assertThrows(InvalidSensorValueException.class, () ->
                service.modifyValues(new SensorChange("set", -100), 1L),
                "Expected InvalidSensorValueException to be thrown, because" +
                "set value is below minValue.");
    }

    /**
     * Test checks if setting value above maxValue in sensor throws an exception.
     */
    @Test
    void verifyModifyValueExceedMaxValue(){
        when(sensorRepository.findById(1L)).thenReturn(Optional.of(sensor));

        InvalidSensorValueException thrownException = assertThrows(InvalidSensorValueException.class, () ->
                        service.modifyValues(new SensorChange("set", 150), 1L),
                "Expected InvalidSensorValueException to be thrown, because" +
                        "set value is above maxValue.");
    }

    /**
     * Test checks whether passing a negative value to increment/decrement operation
     * throws an exception.
     */
    @Test
    public void isExceptionThrownWhenPassedNegative(){
        when(sensorRepository.findById(1L)).thenReturn(Optional.of(sensor));

        NegativeValuePassedException thrownException = assertThrows(NegativeValuePassedException.class, () ->
                        service.modifyValues(new SensorChange("add", -10), 1L),
                "Expected NegativeValuePassedException to be thrown, because" +
                        "passed value is negative.");
    }

    /**
     * Test checks whether it's possible to pass a negative value to
     * set it to the sensor.
     */
    @Test
    public void isSetCorrectlyBehaving(){
        when(sensorRepository.findById(1L)).thenReturn(Optional.of(sensor));
        assertDoesNotThrow(() -> service.modifyValues(new SensorChange("set", -10), 1L),
                "Expected NegativeValuePassedException not to be thrown," +
                        "because the operation is 'set' and setting a negative " +
                        "value should be allowed.");
    }

}