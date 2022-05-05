package com.example.sensors.EntityBuilder;

import com.example.sensors.Engine.Engine;
import com.example.sensors.Engine.EngineRepository;
import com.example.sensors.POJOs.SensorInformation;
import com.example.sensors.Sensor.Sensor;
import com.example.sensors.Sensor.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Class responsible for creating Entities based on the retrieved YAML entries
 * and mapped to SensorInformation class.
 * The class divides sensors into two categories - temperature and pressure ones.
 * And later creates and persists Entities to the database.
 */
@Component
public class EntityBuilder {
    /**
     * Sensor Repository to persist object into the database.
     */
    private final SensorRepository sensorRepository;
    /**
     * Engine Repository to persist object into the database.
     */
    private final EngineRepository engineRepository;

    /**
     * Constructor with dependency injection of the repositories.
     * @param sensorRepository Sensor Repository
     * @param engineRepository Engine Repository
     */
    @Autowired
    public EntityBuilder(SensorRepository sensorRepository, EngineRepository engineRepository) {
        this.sensorRepository = sensorRepository;
        this.engineRepository = engineRepository;
    }

    /**
     * Method divides all the sensors into the separate lists based on the type of the object.
     * @param allSensors List containing all the sensors.
     * @param temperatureSensors List containing only temperature sensors.
     * @param pressureSensors List containing only pressure sensors.
     */
    public void separateSensors(List<SensorInformation> allSensors,
                                List<SensorInformation> temperatureSensors,
                                List<SensorInformation> pressureSensors){
        for (SensorInformation sensor :
                allSensors) {
            if (sensor.getMaster_sensor_id() == null) pressureSensors.add(sensor);
            else temperatureSensors.add(sensor);
        }

    }


    /**
     * Method creates and persists objects to the database.
     * First it calls the separateSensors() method in order to separate temperature and pressure sensors.
     * Later on it manages all the relations across them and persist them into the database.
     * @param sensors List of sensors.
     */
    public void createObjects(List<SensorInformation> sensors) {

        List<SensorInformation> tempSensors = new ArrayList<>();
        List<SensorInformation> pressureSensors = new ArrayList<>();
        // separating sensors (no point in iterating on the same one, where some objects are useless to us)
        this.separateSensors(sensors, tempSensors, pressureSensors);

        for (SensorInformation sensor :
                pressureSensors) {
            // creating new Engine and calling utility method in order to get relations right
            Engine engine = new Engine(Long.parseLong(sensor.getEngine()), sensor.getName());
            Sensor newSensor = new Sensor(sensor.getId(), sensor.getType(), sensor.getValue(), sensor.getMax_value(),
                    sensor.getMin_value(), null);
            engine.addSensor(newSensor);
            engineRepository.save(engine);

            for (SensorInformation tempSensor :
                    tempSensors) {
                // iterating over temperature sensors to add ones corresponding to specific engine
                if (tempSensor.getMaster_sensor_id().equals(newSensor.getId())) {
                    Sensor newTempSensor = new Sensor(tempSensor.getId(), tempSensor.getType(), tempSensor.getValue(),
                            tempSensor.getMax_value(), tempSensor.getMin_value(), newSensor.getEngine());
                    // calling utility method to map relations correctly
                    newSensor.addChildSensor(newTempSensor);
                    sensorRepository.save(newTempSensor);
                }
            }
        }
    }
}
