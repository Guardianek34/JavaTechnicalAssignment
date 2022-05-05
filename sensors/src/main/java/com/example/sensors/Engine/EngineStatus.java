package com.example.sensors.Engine;

import com.example.sensors.Sensor.Sensor;

import java.util.List;

/**
 * Class contains methods used for determination
 * if engine is OK.
 */
public class EngineStatus {

    /**
     * Method returns state in which the engine is based on
     * values coming from temperature and pressure sensors
     * associated with the engine.
     * @param sensors List of sensors associated with the engine.
     * @param temp_threshold Temperature Threshold.
     * @param pressure_threshold Pressure Threshold.
     * @return false when
     *         - value coming from pressure sensor is lower than pressure_threshold
     *         - at least one one value coming from temperature sensor is higher than temp_threshold
     *         otherwise returns true
     */
    public static boolean isEngineOK(List<Sensor> sensors,
                                     Integer temp_threshold,
                                     Integer pressure_threshold)
    {
        boolean isTemperatureOK = false;
        boolean isPressureOK = false;

        for (Sensor sensor:
                sensors) {
            String type = sensor.getType();
            Integer value = sensor.getValue();
            // setting up flags if the measured value exceeds threshold
            if (type.equals("temperature") && value < temp_threshold) {
                isTemperatureOK = true;
            } else if (type.equals("pressure") && value > pressure_threshold) {
                isPressureOK = true;
            }
        }

        // both conditions must be met in order for engine to be registered as faulty
        if(isTemperatureOK && isPressureOK) return true;
        else return false;
    }
}
