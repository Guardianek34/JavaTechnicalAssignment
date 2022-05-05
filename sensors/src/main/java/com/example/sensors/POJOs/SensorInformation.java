package com.example.sensors.POJOs;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Object used for the sake of mapping objects
 * from YAML to Java class representation.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SensorInformation {
    /**
     * Object's id
     */
    private Long id;
    /**
     * Superior sensor
     * (in our example Pressure is superior to Temperature Sensor)
     */
    private Long master_sensor_id;
    /**
     * ID of Engine
     */
    private String engine;
    /**
     * Type of the Sensor
     */
    private String type;
    /**
     * Engine Name
     */
    private String name;
    /**
     * Sensor's value
     */
    private Integer value;
    /**
     * Minimum sensor's value.
     */
    private Integer min_value;
    /**
     * Maximum sensor's value.
     */
    private Integer max_value;
}
