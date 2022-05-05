package com.example.sensors.Parsers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Class contains tests for the URLParser class.
 */
public class URLParserTest {

    static Stream<Arguments> invalidConfigParameters() {
        return Stream.of(
                Arguments.of((Object) new String[]{"https://github.com/relayr/pdm-test/blob/master/sensors.yml",
                        "config=https://youtube.com/relayr/pdm-test/blob/master/sensors.yml",
                        "config=github.com/relayr/pdm-test/blob/sensors.yml"}),
                Arguments.of((Object) new String[]{"https://www.google.pl/",
                        "-config=https://github.com/relayr/pdm-test/blob/master/sensors.yml",
                        "--Config=https://github.com/relayr/pdm-test/blob/master/sensors.yml"})
        );
    }

    /**
     * Test verifies if exception is thrown when user has not
     * provided an CLI argument starting with --config=.
     * @param args CLI arguments
     */
    @ParameterizedTest
    @MethodSource("invalidConfigParameters")
    public void verifyPassedCLIArguments(String... args){
        IllegalArgumentException thrownException = assertThrows(IllegalArgumentException.class, () ->
                URLParser.checkAppArgs(args), "Expected IllegalArgumentException to be thrown, because" +
                "passed arguments doesn't contain argument starting with --config=."
        );
    }


    /**
     * Test verifies whether URL preceded with "--config" is properly
     * modified ("--config" part should be deleted from the string)
     */
    @Test
    public void verifyReplaceOperation(){
        String url1 = "--config=https://github.com/relayr/pdm-test/blob/master/sensors.yml";
        String url2 = "github.com/relayr/--configpdm-test/blob/master/sensors.yml";
        String url3 = "github.com/relayr/sensors.yml";
        String[] array = {url1, url2, url3};
        String expected = "https://github.com/relayr/pdm-test/blob/master/sensors.yml";

        String result = URLParser.checkAppArgs(array);

        assertEquals(result, expected);
    }

    /**
     * Test verifies if IllegalArgumentException is thrown when URL
     * contains domain different than github.com
     * @param url URL to verify
     */
    @ParameterizedTest
    @ValueSource(strings = {
            "https://google.com/relayr/pdm-test/blob/master/sensors.yml",
            "https://reddit.com",
            "https://youtube.com"
    })
    public void verifyDifferentDomain(String url){
        IllegalArgumentException thrownException = assertThrows(IllegalArgumentException.class, () ->
                URLParser.parseURLToRequest(url), "Expected IllegalArgumentException to be thrown, because" +
                "passed URL (in a form of String) is not from the github.com domain."
        );
    }


    /**
     * Test checks if a given URL is too short and cannot contain path to the file.
     * It should throw an exception in this scenario.
     * @param url URL to verify
     */
    @ParameterizedTest
    @ValueSource(strings = {
        "https://github.com/test-user/project_name/main/file.yml",
        "https://github.com/test-user/project_name/file.yml",
        "https://google.com"
    })
    public void verifyTooShortURL(String url){
        IllegalArgumentException thrownException = assertThrows(IllegalArgumentException.class, () ->
                URLParser.parseURLToRequest(url), "Expected IllegalArgumentException to be thrown, because" +
                "passed URL (in a form of String) is too short to be a path to file in repo."
        );
    }

    /**
     * Test checks if a parsed URL meets the criteria of a request to Github API
     * to retrieve information about the file (and its content).
     */
    @Test
    public void verifyResultingURL(){
        String urlToParse = "https://github.com/relayr/pdm-test/blob/master/sensors.yml";
        String expectedURL = "https://api.github.com/repos/relayr/pdm-test/contents/sensors.yml";

        String parsedURL = URLParser.parseURLToRequest(urlToParse);

        assertEquals(parsedURL, expectedURL);
    }

}
