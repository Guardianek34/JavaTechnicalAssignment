package com.example.sensors.Parsers;
import com.example.sensors.POJOs.SensorInformation;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;


import java.io.IOException;
import java.util.List;

/**
 * Class uses Jackson in order to properly deserialize YAML content into the SensorInfo Objects.
 * SensorInfo contains all the fields about the sensors (both temperature and pressure ones).
 */
public class YAMLObjectsParser {

    public static List<SensorInformation> deserializeObjects(String fileContent) throws IOException {

        // replacing '-' characters due to the fact that they cannot be used as a variable names (and fields in a class)
        fileContent = fileContent.replace("master-sensor-id", "master_sensor_id");

        // declaring mapper to work with YAML
        var mapper = new ObjectMapper(new YAMLFactory());


        // deserializing objects
        TypeReference<List<SensorInformation>> typeReference = new TypeReference<>(){};

        return mapper.readValue(fileContent, typeReference);
    }
}
