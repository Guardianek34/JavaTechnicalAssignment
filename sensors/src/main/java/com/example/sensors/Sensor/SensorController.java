package com.example.sensors.Sensor;

import com.example.sensors.POJOs.SensorChange;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Basic RestController.
 */
@RestController
public class SensorController {

    /**
     * Sensor Service
     */
    private final SensorService sensorService;

    /**
     * Parametrized constructor with injected service.
     * @param sensorService Sensor Service
     */
    @Autowired
    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    /**
     * Method that returns all the sensors.
     * @return List of all the objects (Sensors) from the Sensor table.
     */
    @GetMapping("/sensors")
    @Operation(summary = "Retrieve information about all sensors.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
            description = "Retrieved all the sensors.",
            content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
            description = "Requested resource has not been found.",
            content = @Content)
    })
    public List<Sensor> getSensors(){
        return sensorService.getSensors();
    }

    /**
     * Method that performs an operation on the specific Sensor (its value).
     * @param change Request Body consisting of type of operation and the value
     *               Currently available operations: "increment", "decrement", "set".
     *               Value has to be an Integer.
     * @param id ID of a Sensor in database.
     * @return Sensor with a changed value if an operation has succeeded.
     */
    @PostMapping("/sensors/{id}")
    @Operation(summary = "Change the value of the sensor.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
            description = "Successfully modified the sensor's value.",
            content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400",
            description = "Failed to modify the sensor's value.",
            content = @Content)
    })
    public ResponseEntity<Sensor> changeValue(@RequestBody SensorChange change,
                                      @Parameter(description = "ID of sensor to be modified.")
                                      @PathVariable long id){
        return sensorService.modifyValues(change, id);
    }
}
