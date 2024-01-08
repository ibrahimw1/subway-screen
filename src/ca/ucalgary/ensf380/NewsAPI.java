package ca.ucalgary.ensf380;

import java.io.BufferedReader;   
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * The NewsAPI class is responsible for fetching news data from the NewsCatcher API.
 * @author group1
 * @version 1.0
 */
public class NewsAPI {

    private static final String API_KEY = "KoPlAQT4KMvgsCVEk7WPayXY_Qkfg-y35oAF55GVTCg";

    //DO NOT USE: 9ymO_SvSKzFHAPU-sJGh-s5JOHrhbU8wKsLmB9Js_PQ
    //USE THIS ONE: 6hca-7RuSwLaHRCbRGMoLXEBTKfO3pMWduz6IocXKeU
    //Third one: KoPlAQT4KMvgsCVEk7WPayXY_Qkfg-y35oAF55GVTCg
    
    
    /**
     * Fetches news data for the given keyword from the NewsCatcher API.
     *
     * @param keyword The keyword to search for news articles.
     * @return A string containing the JSON response from the API.
     */
    public String getNews(String keyword) throws IllegalArgumentException {
        // If the keyword is null or empty, throw an IllegalArgumentException
        if (keyword == null || keyword.isEmpty()) {
            throw new IllegalArgumentException("Keyword cannot be null or empty");
        }
        
        // Create a StringBuilder to store the JSON response from the API
        StringBuilder result = new StringBuilder();

        try {
            // Encode the keyword using UTF-8 encoding to ensure it is properly formatted for the URL
            String encodedKeyword = URLEncoder.encode(keyword, StandardCharsets.UTF_8);
            
            // Construct the URL to call the NewsCatcher API with the encoded keyword
            String urlString = "https://api.newscatcherapi.com/v2/search?q=" + encodedKeyword;

            // Create a URL object using the constructed URL string
            URL url = new URL(urlString);
            
            // Open a connection to the NewsCatcher API using HTTP GET method
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            
            // Set the API key as a request property to authenticate the request
            conn.setRequestProperty("x-api-key", API_KEY);

            // Create a BufferedReader to read the response from the API
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            
            // Read the response line by line and append it to the result StringBuilder
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            
            // Close the BufferedReader after reading the response
            rd.close();
        } catch (Exception e) {
            // If any exception occurs during the API call, print the stack trace for debugging purposes
            e.printStackTrace();
        }

        // Return the JSON response as a string
        return result.toString();
    }

}
