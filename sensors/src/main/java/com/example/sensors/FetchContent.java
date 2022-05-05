package com.example.sensors;
import com.example.sensors.EntityBuilder.EntityBuilder;
import com.example.sensors.POJOs.GithubResponse;
import com.example.sensors.POJOs.SensorInformation;
import com.example.sensors.Parsers.URLParser;
import com.example.sensors.Parsers.YAMLObjectsParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.List;

/**
 * Class to organize process of fetching data from the Github repository into our database.
 * Due to the fact it implements CommandLineRunner, it runs automatically.
 */
@Component
public class FetchContent implements CommandLineRunner {

    // using restTemplate in order to retrieve resources from Github's API (using GET method)
    private final RestTemplate restTemplate;

    // using EntityBuilder to create and save objects into database
    private final EntityBuilder entityBuilder;

    /**
     * Constructor with injected params.
     * @param restTemplate RestTemplate object
     * @param entityBuilder EntityBuilder object
     */
    @Autowired
    public FetchContent(RestTemplate restTemplate, EntityBuilder entityBuilder) {
        this.restTemplate = restTemplate;
        this.entityBuilder = entityBuilder;
    }


    /**
     * Method is being called after application context has been initialised
     * It organizes the process of creating the objects.
     * @param args Passed CLI arguments.
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        // Checking if there was passed a correct URL to Github repo
        String url = URLParser.checkAppArgs(args);

        // Getting the whole response about the file and storing it in an object of GithubResponse class
        GithubResponse response = restTemplate.getForObject(URLParser.parseURLToRequest(url), GithubResponse.class);

        // Decoding the file's content (at this particular moment it's Base64 encoded)
        String decoded = new String(Base64.getMimeDecoder().decode(response.getContent()));

        // Deserializing YAML file's content into objects.
        List<SensorInformation> sensors = YAMLObjectsParser.deserializeObjects(decoded);

        // Create and save objects to database.
        entityBuilder.createObjects(sensors);

    }
}