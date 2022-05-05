package com.example.sensors.Engine;

import com.example.sensors.Sensor.Sensor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

/**
 * Service for the Engine.
 */
@Service
public class EngineService {


    /**
     * Repository
     */
    private final EngineRepository engineRepository;

    /**
     * Constructor with injected repository.
     * @param engineRepository Engine Repository.
     */
    @Autowired
    public EngineService(EngineRepository engineRepository) {
        this.engineRepository = engineRepository;
    }

    /**
     * Method adds an engine to the db.
     * @param engine Entity Engine
     * @return Added object.
     */
    public Engine addEngine(Engine engine) {
        return engineRepository.save(engine);
    }

    /**
     * Method returns all faulty engines.
     * Faulty Engine is defined as one that:
     * - corresponding pressure sensor measures value below passed pressure_threshold
     * - one of the corresponding temperature sensors measures value above passed temp_threshold
     * @param pressure_threshold Pressure Threshold
     * @param temp_threshold Temperature Threshold
     * @return List of all the faulty engines.
     */
    public List<Engine> getFaulty(Integer pressure_threshold, Integer temp_threshold) {
        List<Engine> allEngines = engineRepository.findAll();
        List<Engine> faultyEngines = new ArrayList<>();

        for (Engine engine:
             allEngines) {

            // getting sensors associated with each engine
            List<Sensor> sensors = engine.getSensors();

            boolean isEngineOK = EngineStatus.isEngineOK(
                    sensors,
                    temp_threshold,
                    pressure_threshold);

            if(!isEngineOK) faultyEngines.add(engine);

        }
        return faultyEngines;
    }
}
