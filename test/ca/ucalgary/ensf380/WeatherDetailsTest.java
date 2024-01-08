package ca.ucalgary.ensf380;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Test class for WeatherDetails.
 * @author group1
 * @version 1.0
 */
public class WeatherDetailsTest {

    /**
     * Test getters and setters for WeatherDetails.
     */
    @Test
    public void testWeatherDetailsGettersAndSetters() {
        // Initialize a WeatherDetails object
        WeatherDetails weatherDetails = new WeatherDetails();

        // Set values using setters
        weatherDetails.setTemperature("20.5");
        weatherDetails.setWeatherCondition("Cloudy");
        weatherDetails.setHumidity("70");
        weatherDetails.setWindSpeed("5.8");
        weatherDetails.setDate("1629526931");

        // Test getters to check if the values were set correctly
        assertEquals("20.5", weatherDetails.getTemperature());
        assertEquals("Cloudy", weatherDetails.getWeatherCondition());
        assertEquals("70", weatherDetails.getHumidity());
        assertEquals("5.8", weatherDetails.getWindSpeed());
        assertEquals("1629526931", weatherDetails.getDate());
    }
}
