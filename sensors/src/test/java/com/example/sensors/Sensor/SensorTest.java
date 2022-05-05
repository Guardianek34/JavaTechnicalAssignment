package com.example.sensors.Sensor;

import com.example.sensors.Engine.Engine;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SensorTest {

    private Sensor sensor = new Sensor(
            1L,
            "temperature",
            11,
            150,
            0,
            null);

    /**
     * Testing utility method addEngine() that should add the objects bidirectional.
     */
    @Test
    void addEngineTest() {
        Engine engine = new Engine(1L, "Engine 123");

        sensor.addEngine(engine);

        assertEquals(sensor.getEngine(), engine);
        assertTrue(engine.getSensors().contains(sensor));
    }

    /**
     * Testing utility method addParentSensor() that should add the objects bidirectional.
     */
    @Test
    void addParentSensorTest() {
        Sensor parentSensor = new Sensor(2L, "pressure",
                7, 200, 5, null);

        sensor.addParentSensor(parentSensor);

        assertEquals(sensor.getParentSensor(), parentSensor);
        assertTrue(parentSensor.getChildSensors().contains(sensor));

    }

    /**
     * Testing utility method addChildSensor() that should add the objects bidirectional.
     */
    @Test
    void addChildSensorTest() {
        Sensor childSensor = new Sensor(2L, "pressure",
                7, 200, 5, null);

        sensor.addChildSensor(childSensor);

        assertTrue(sensor.getChildSensors().contains(childSensor));
        assertEquals(childSensor.getParentSensor(), sensor);
    }
}