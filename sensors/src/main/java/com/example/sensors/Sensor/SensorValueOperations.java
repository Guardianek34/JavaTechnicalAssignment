package com.example.sensors.Sensor;

import com.example.sensors.Exceptions.InvalidSensorOperationException;
import com.example.sensors.POJOs.SensorChange;

/**
 * Class contains methods performing operations on the value of Sensor.
 */
public class SensorValueOperations {

    /**
     * Method performs arithemtic operation on the sensor's value.
     * Currently supports "increment", "decrement" and "set" operations.
     * @param currentSensorValue Current value of the Sensor Object.
     * @param change Object containing information about the operation type
     *               and the value.
     * @return Value after the operation has been performed.
     */
    public static Integer performOperation(Integer currentSensorValue, SensorChange change){
        Integer tempValue = 0;
        switch(change.getOperation())
        {
            case "increment":
                tempValue = currentSensorValue + change.getValue();
                break;
            case "decrement":
                tempValue = currentSensorValue - change.getValue();
                break;
            case "set":
                tempValue = change.getValue();
                break;
            default:
                throw new InvalidSensorOperationException(change.getOperation());
        }
        return tempValue;
    }
}
