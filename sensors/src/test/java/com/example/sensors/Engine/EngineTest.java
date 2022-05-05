package com.example.sensors.Engine;

import com.example.sensors.Sensor.Sensor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class contains tests for Engine Entity.
 */
class EngineTest {

    /**
     * Class tests utility method addSensor() that should create
     * bidirectional relation between Engine and Sensor. (1-N)
     */
    @Test
    void addSensorTest() {
        Engine engine = new Engine(1L, "Engine 123");
        Sensor sensor = new Sensor(
                1L,
                "temperature",
                11,
                150,
                0,
                null);

        engine.addSensor(sensor);

        assertTrue(engine.getSensors().contains(sensor));
        assertEquals(sensor.getEngine(), engine);
    }
}