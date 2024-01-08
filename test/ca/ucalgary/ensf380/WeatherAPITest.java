package ca.ucalgary.ensf380;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Test class for WeatherAPI.
 * @author group1
 * @version 1.0
 */
public class WeatherAPITest {

    /**
     * Test the fetchWeather method of WeatherAPI.
     */
    @Test
    public void testFetchWeather() {
        // Initialize the WeatherAPI instance
        WeatherAPI weatherAPI = new WeatherAPI();
        
        // Test fetching weather data for a specific city code (you can change the city code for your location)
        String cityCode = "2643743"; // City code for London, UK
        WeatherDetails weatherDetails = weatherAPI.fetchWeather(cityCode);
        
        // Assert that the weatherDetails object is not null
        assertNotNull(weatherDetails);
        
        // Assert that the temperature, weather condition, humidity, wind speed, and date are not "No match found"
        assertNotEquals("No match found", weatherDetails.getTemperature());
        assertNotEquals("No match found", weatherDetails.getWeatherCondition());
        assertNotEquals("No match found", weatherDetails.getHumidity());
        assertNotEquals("No match found", weatherDetails.getWindSpeed());
        assertNotEquals("No match found", weatherDetails.getDate());
    }
}
