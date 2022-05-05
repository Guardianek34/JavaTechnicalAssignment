package com.example.sensors.Engine;

import com.example.sensors.Sensor.Sensor;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class contains methods testing EngineStatus class.
 */
class EngineStatusTest {

    private Integer temp_threshold = 10;
    private Integer pressure_threshold = 50;
    private Sensor temperatureTooHigh = new Sensor(1L, "temperature", 100,
            150, 0, null);
    Sensor temperatureTooHigh2 = new Sensor(2L, "temperature", 66,
            150, 0, null);
    private Sensor temperatureGood = new Sensor(2L, "temperature", 5,
            150, 0, null);
    Sensor pressureGood = new Sensor(3L, "pressure", 60,
            150, 0, null);
    Sensor pressureBad = new Sensor(3L, "pressure", 30,
            150, 0, null);

    @Test
    void isEngineOKTestPressureGoodTemperatureGood() {
        List<Sensor> sensors = Arrays.asList(temperatureTooHigh, temperatureGood, pressureGood);

        assertTrue(EngineStatus.isEngineOK(sensors, temp_threshold, pressure_threshold));
    }

    @Test
    void isEngineOKTestPressureGoodTemperatureBad() {
        List<Sensor> sensors = Arrays.asList(temperatureTooHigh, temperatureTooHigh, pressureGood);

        assertFalse(EngineStatus.isEngineOK(sensors, temp_threshold, pressure_threshold));
    }

    @Test
    void isEngineOKTestPressureBadTemperatureBad() {
        List<Sensor> sensors = Arrays.asList(temperatureTooHigh, temperatureTooHigh, pressureBad);

        assertFalse(EngineStatus.isEngineOK(sensors, temp_threshold, pressure_threshold));
    }

    @Test
    void isEngineOKTestPressureBadTemperatureGood() {
        List<Sensor> sensors = Arrays.asList(temperatureGood, temperatureGood, pressureBad);

        assertFalse(EngineStatus.isEngineOK(sensors, temp_threshold, pressure_threshold));
    }
}