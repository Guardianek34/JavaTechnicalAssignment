package com.example.sensors.Parsers;

/**
 * Simple class that is parsing URL given as a param by user to form a GET Request to GitHub's API.
 * Throws IllegalArgumentExceptions in case the given input is not valid.
 */
public class URLParser {

    /**
     * Method parses the URL to the form of request to GitHub's API.
     * @param url URL to the file inside the repository on GitHub.
     * @return GET Request in a form of String.
     */
    public static String parseURLToRequest(String url){
        String[] urlParts = url.split("/"); //splitting the given URL

        if(urlParts.length < 8) {
            throw new IllegalArgumentException("Given URL is invalid.");
        }

        // urlParts[0] and [1] are simple covering "http://" part
        String site = urlParts[2];
        String ownerName = urlParts[3];
        String repo = urlParts[4];

        if(!site.equals("github.com")){
            throw new IllegalArgumentException("Given URL is not from a github.com domain.");
        }

        String path = "";
        // omitting blob/{branch} (urlParts[5] and [6])
        // if the string had been way longer - way to go would be
        // to use StringBuilder (comment added because of a warning)
        for(int i = 7; i < urlParts.length; i++){
            path += urlParts[i] + "/";
        }

        // removing last slash from the URL
        path = path.substring(0, path.length() - 1);

        // You can look for this API endpoint at this link below.
        // https://docs.github.com/en/rest/reference/repos#contents
        // Take it also into consideration that without personal OAUTH2 token, you can do only 50 requests an hour.
        // (up to 2500 or so when sending authenticated requests).
        // For the sake of this example I haven't included my personal token.
        return "https://api.github.com/repos/" + ownerName + "/" + repo + "/contents/" + path;
    }

    /**
     * Method checks whether there is a param starting with --config.
     * @param args CLI Arguments passed by the user.
     * @return Correct argument (there can be several passed), without --config keyword.
     */
    public static String checkAppArgs(String... args){
        for (String param:
                args) {
            if (param.startsWith("--config=")){
                return param.replace("--config=", "");
            }
        }

        throw new IllegalArgumentException("--config=repoURL is required for the app to work.");
    }
}