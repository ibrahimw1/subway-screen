
package ca.ucalgary.ensf380;

import java.io.BufferedReader;   
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.*;

/**
 * The WeatherAPI class is responsible for fetching weather data from the OpenWeatherMap API.
 * @author group1
 * @version 1.0
 */
public class WeatherAPI   {

    private static final String API_KEY = "90e9cd43cfcd4f957b4ec34a8e2bf339";

  //007d2a8fdbafdac5e2c7b8a96a8bd6e2
  //90e9cd43cfcd4f957b4ec34a8e2bf339
  //NEWEST KEY: 0d2b75e474188e8bba8cb4d82fdbd3fe
    /**
     * Fetches weather data for the specified city code from the OpenWeatherMap API.
     *
     * @param cityCode The city code representing the location for which weather data is requested.
     * @return A WeatherDetails object containing the parsed weather data.
     */
    
    public WeatherDetails fetchWeather(String cityCode) {
        // Fetch the raw HTML page containing weather data for the specified city
        String pageHtml = fetchWeatherPage(cityCode);

        // Parse the weather data from the HTML and create a WeatherDetails object
        return parseWeatherData(pageHtml);
    }

    /**
     * Fetches the raw HTML page containing weather data for the specified city code from the OpenWeatherMap API.
     *
     * @param cityCode The city code representing the location for which weather data is requested.
     * @return A string containing the raw HTML page.
     */
    private String fetchWeatherPage(String cityCode) {
        StringBuilder result = new StringBuilder();
        try {
            // Construct the URL to call the OpenWeatherMap API with the city code and API key
            String urlString = "https://api.openweathermap.org/data/2.5/weather?id=" + cityCode + "&appid=" + API_KEY;

            // Create a URL object using the constructed URL string
            URL url = new URL(urlString);

            // Open a connection to the OpenWeatherMap API using HTTP GET method
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

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

        // Return the raw HTML page as a string
        return result.toString();
    }

    /**
     * Parses the weather data from the raw HTML page and creates a WeatherDetails object.
     *
     * @param html The raw HTML page containing weather data.
     * @return A WeatherDetails object containing the parsed weather data.
     */
    private WeatherDetails parseWeatherData(String html) {
        WeatherDetails details = new WeatherDetails();

     // Regular expressions to match temperature, weather condition, wind speed, humidity, and date
        // Temperature regex matches a number with decimal point, representing the temperature in Celsius
        String temperature = "\"temp\":(\\d+\\.\\d+)";
        // Weather condition regex matches any non-empty string between the double quotes after "description"
        String weather = "\"description\":\"([^\"]+)\"";
        // Wind speed regex matches a number with or without decimal point, representing wind speed in meters per second
        String wind = "\"speed\":(\\d+(\\.\\d+)?)";
        // Humidity regex matches a number representing the humidity percentage
        String humidity = "\"humidity\":(\\d+)";
        // Date regex matches a number representing the date in Unix timestamp format
        String date = "\"dt\":(\\d+)";

        // Parse the weather data using regular expressions and set it in the WeatherDetails object
        details.setTemperature(findMatch(temperature, html));
        details.setWeatherCondition(findMatch(weather, html));
        details.setWindSpeed(findMatch(wind, html));
        details.setHumidity(findMatch(humidity, html));
        details.setDate(findMatch(date, html));

        return details;
    }

    /**
     * Helper method to find a match using a regular expression pattern in a given text.
     *
     * @param regexPattern The regular expression pattern to match.
     * @param text The text in which to find the match.
     * @return The matched substring or "No match found" if no match is found.
     */
    private String findMatch(String regexPattern, String text) {
        // Compile the regular expression pattern and create a matcher for the given text
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(text);

        // If a match is found, return the first matching group (in parentheses), otherwise return "No match found"
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return "No match found";
        }
    }
    
    
}
