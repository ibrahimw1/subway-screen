package ca.ucalgary.ensf380;
/**
 * The WeatherDetails class represents the weather data for a specific location.
 * @author group1
 * @version 1.0
 */
public class WeatherDetails extends WeatherAPI {
    private String temperature;
    private String weatherCondition;
    private String humidity;
    private String windSpeed;
    private String date;

    /**
     * Get the temperature in Celsius.
     *
     * @return The temperature as a String.
     */
    public String getTemperature() {
        return temperature;
    }

    /**
     * Set the temperature in Celsius.
     *
     * @param temperature The temperature to set.
     */
    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    /**
     * Get the weather condition.
     *
     * @return The weather condition as a String.
     */
    public String getWeatherCondition() {
        return weatherCondition;
    }

    /**
     * Set the weather condition.
     *
     * @param weatherCondition The weather condition to set.
     */
    public void setWeatherCondition(String weatherCondition) {
        this.weatherCondition = weatherCondition;
    }

    /**
     * Get the humidity percentage.
     *
     * @return The humidity as a String.
     */
    public String getHumidity() {
        return humidity;
    }

    /**
     * Set the humidity percentage.
     *
     * @param humidity The humidity to set.
     */
    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    /**
     * Get the wind speed in meters per second.
     *
     * @return The wind speed as a String.
     */
    public String getWindSpeed() {
        return windSpeed;
    }

    /**
     * Set the wind speed in meters per second.
     *
     * @param windSpeed The wind speed to set.
     */
    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    /**
     * Get the date of the weather data.
     *
     * @return The date as a String.
     */
    public String getDate() {
        return date;
    }

    /**
     * Set the date of the weather data.
     *
     * @param date The date to set.
     */
    public void setDate(String date) {
        this.date = date;
    }
}
