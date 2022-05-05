package com.example.sensors.Engine;

import com.example.sensors.Sensor.Sensor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

/**
 * Class contains tests for Engine Service.
 */
@ExtendWith(MockitoExtension.class)
class EngineServiceTest {

    @Mock
    private EngineRepository engineRepository;

    @InjectMocks
    private EngineService service;

    /**
     * Test checks whether engineRepository.save() method is being called
     * while invoking addEngine() method from service.
     */
    @Test
    void addEngineTest() {
        Engine engine = new Engine(1L, "Engine 123");
        service.addEngine(engine);
        verify(engineRepository).save(engine);
    }


    /**
     * Method verifies whether engines are added to list
     * when considered faulty.
     */
    @Test
    void getFaultyTest() {
        Engine engine = new Engine(4L, "Engine1");
        Sensor badTempSensor = new Sensor(
                1L,
                "temperature",
                90,
                100,
                10,
                null
        );
        Sensor goodPressureSensor = new Sensor(
                2L,
                "pressure",
                25,
                150,
                20,
                null
        );

        engine.setSensors(Arrays.asList(badTempSensor, goodPressureSensor));
        badTempSensor.setEngine(engine);
        goodPressureSensor.setEngine(engine);

        doReturn(List.of(engine)).when(engineRepository).findAll();

        List<Engine> faultyEngines = service.getFaulty(20, 50);

        assertTrue(faultyEngines.contains(engine));
    }
}