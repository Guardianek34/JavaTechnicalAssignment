package com.example.sensors.POJOs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Simple class to store data from response
 * In the current state of implementation, we only use file's content nevertheless,
 * but included the rest of the fields to use.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GithubResponse {
    private String name;
    private String path;
    private String sha;
    private Integer size;
    private String url;
    private String html_url;
    private String git_url;
    private String download_url;
    private String type;
    /**
     * File content's. Encrypted using BASE64.
     */
    private String content;
    private String encoding;
    private GithubLinksResponse _links;
}
