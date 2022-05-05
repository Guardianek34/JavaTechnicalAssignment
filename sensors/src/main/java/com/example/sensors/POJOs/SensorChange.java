package com.example.sensors.POJOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * POJO class that is being passed in a body of GET request
 * regarding the Sensor's value modification.
 * localhost:{port}/sensors/{id}
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SensorChange {
    private String operation;
    private Integer value;
}
