package com.example.sensors.Engine;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Basic RestController.
 */
@RestController
public class EngineController {

    /**
     * Service.
     */
    private final EngineService engineService;

    /**
     * Constructor with dependency injected service.
     * @param engineService Engine Service.
     */
    @Autowired
    public EngineController(EngineService engineService) {
        this.engineService = engineService;
    }

    /**
     * Endpoint returns all the faulty Engines as a response.
     * @param pressure_threshold Pressure Threshold Value
     * @param temp_threshold Temperature Threshold Value
     * @return List of faulty Engines (entities)
     */

    @GetMapping("/engines")
    @Operation(summary = "Retrieve all faulty engines.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Retrieved all the faulty engines.",
                    content = {@Content(mediaType = "application/json")})
    })
    public List<Engine> getFaultyEngines(@Parameter(description = "Threshold value for pressure sensors.")
                                         @RequestParam(required = true) Integer pressure_threshold,
                                         @Parameter(description = "Threshold value for temperature sensors.")
                                         @RequestParam(required = true) Integer temp_threshold){

        return engineService.getFaulty(pressure_threshold, temp_threshold);
    }

    /**
     * Endpoint adds the json object to the database.
     * @param engine Engine in a form of json format. (@RequestBody)
     * @return Added object.
     */
    @PostMapping("/engines")
    @Operation(summary = "Add a new engine to the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Successfully added the object.",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400",
                    description = "Invalid Request. Operation could not be performed.",
                    content = @Content)})
    public Engine addEngine(@RequestBody Engine engine){
        return engineService.addEngine(engine);
    }
}
