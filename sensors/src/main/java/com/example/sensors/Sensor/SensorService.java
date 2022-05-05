package com.example.sensors.Sensor;

import com.example.sensors.Exceptions.NegativeValuePassedException;
import com.example.sensors.POJOs.SensorChange;
import com.example.sensors.Exceptions.InvalidSensorOperationException;
import com.example.sensors.Exceptions.InvalidSensorValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for the Sensor Entity.
 */
@Service
public class SensorService {

    /**
     * Injected SensorRepository.
     */
    private final SensorRepository sensorRepository;

    /**
     * Constructor with one argument (that is being injected)
     * @param sensorRepository Injected repository.
     */
    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    /**
     * Method to return all the Sensors from the Database.
     * @return List containing Sensors.
     */
    public List<Sensor> getSensors() {
        return sensorRepository.findAll();
    }

    /**
     * Method used to fetch a Sensor from the database and perform and modify its value.
     * Method checks whether the value is in the range of (minValue : maxValue).
     * @param change Passed
     * @param id ID of the object to modify.
     * @return Sensor Entity with the performed update operation (changed value).
     */
    public ResponseEntity<Sensor> modifyValues(SensorChange change, long id){
        // searching a sensor to be changed
        Sensor sensorToChange = sensorRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException());

        Integer maxValue = sensorToChange.getMax_value();
        Integer minValue = sensorToChange.getMin_value();

        // assuming we have 'increment' and 'decrement' operations
        // passed value shouldn't be negative
        if(change.getValue() < 0 && !change.getOperation().equals("set")){
            throw new NegativeValuePassedException();
        }

        // based on the operation (in a form of String), we change the value
        Integer tempValue = SensorValueOperations.performOperation(
                sensorToChange.getValue(),
                change
                );

        // checking whether the new value are in the range of <minValue;maxValue>
        if(tempValue <= minValue || tempValue >= maxValue){
            throw new InvalidSensorValueException(tempValue, minValue, maxValue);
        }

        // when the value is correct, we are setting it and performing update (inserting an object)
        sensorToChange.setValue(tempValue);
        sensorRepository.save(sensorToChange);

        return new ResponseEntity<>(sensorToChange, HttpStatus.OK);
    }
}
