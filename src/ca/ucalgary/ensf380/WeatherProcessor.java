package ca.ucalgary.ensf380;

import javax.swing.ImageIcon;  
import java.awt.*;

/**
 * The WeatherProcessor class provides utility methods related to weather data processing.
 * This class offers a way to fetch appropriate weather icons based on the given weather condition.
 * 
 * <p>
 
 * </p>
 * 
 * @author group1 
 * @version 1.0
 */
public class WeatherProcessor {

    /**
     * Returns an ImageIcon representation of the specified weather condition.
     * This method maps the provided weather condition to its corresponding icon,
     * scales the image to the provided width and height, and then returns it.
     *
     * @param weatherCondition The textual representation of the current weather condition.
     * @param width The desired width for the scaled image.
     * @param height The desired height for the scaled image.
     * @return ImageIcon of the weather condition scaled to specified dimensions, or null if the condition is unrecognized.
     */
    public static ImageIcon getWeatherIcon(String weatherCondition, int width, int height) {
        ImageIcon weatherIcon = null;

        // Map the weather condition to its corresponding icon
        if ("clear sky".equalsIgnoreCase(weatherCondition)) {
            weatherIcon = new ImageIcon("res/01d.png");
        } else if ("few clouds".equalsIgnoreCase(weatherCondition)) {
            weatherIcon = new ImageIcon("res/02d.png");
        } else if ("scattered clouds".equalsIgnoreCase(weatherCondition)) {
            weatherIcon = new ImageIcon("res/03d.png");
        } else if ("broken clouds".equalsIgnoreCase(weatherCondition) || "overcast clouds".equalsIgnoreCase(weatherCondition)) {
            weatherIcon = new ImageIcon("res/04d.png");
        } else if (weatherCondition.startsWith("light rain") || weatherCondition.startsWith("moderate rain") ||
                   weatherCondition.startsWith("heavy intensity rain") || weatherCondition.startsWith("very heavy rain") ||
                   weatherCondition.startsWith("extreme rain")) {
            weatherIcon = new ImageIcon("res/10d.png");
        } else if (weatherCondition.startsWith("freezing rain") || weatherCondition.startsWith("light intensity shower rain") ||
                   weatherCondition.startsWith("shower rain") || weatherCondition.startsWith("heavy intensity shower rain") ||
                   weatherCondition.startsWith("ragged shower rain") || weatherCondition.startsWith("light intensity drizzle") ||
                   weatherCondition.startsWith("drizzle") || weatherCondition.startsWith("heavy intensity drizzle") ||
                   weatherCondition.startsWith("light intensity drizzle rain") || weatherCondition.startsWith("drizzle rain") ||
                   weatherCondition.startsWith("heavy intensity drizzle rain") || weatherCondition.startsWith("shower rain and drizzle") ||
                   weatherCondition.startsWith("heavy shower rain and drizzle") || weatherCondition.startsWith("shower drizzle")) {
            weatherIcon = new ImageIcon("res/09d.png");
        } else if (weatherCondition.startsWith("thunderstorm with light rain") || weatherCondition.startsWith("thunderstorm with rain") ||
                   weatherCondition.startsWith("thunderstorm with heavy rain") || weatherCondition.startsWith("light thunderstorm") ||
                   weatherCondition.startsWith("thunderstorm") || weatherCondition.startsWith("heavy thunderstorm")) {
            weatherIcon = new ImageIcon("res/11d.png");
        } else if ("snow".equalsIgnoreCase(weatherCondition)) {
            weatherIcon = new ImageIcon("res/13d.png");
        } else if ("mist".equalsIgnoreCase(weatherCondition) || "smoke".equalsIgnoreCase(weatherCondition) ||
                   "haze".equalsIgnoreCase(weatherCondition) || "dust".equalsIgnoreCase(weatherCondition) ||
                   "fog".equalsIgnoreCase(weatherCondition) || "sand".equalsIgnoreCase(weatherCondition) ||
                   "volcanic ash".equalsIgnoreCase(weatherCondition) || "squalls".equalsIgnoreCase(weatherCondition) ||
                   "tornado".equalsIgnoreCase(weatherCondition) || "sand/dust whirls".equalsIgnoreCase(weatherCondition)) {
            weatherIcon = new ImageIcon("res/50d.png");
        }

        // If a valid weather icon is found, scale it according to provided width and height
        if (weatherIcon != null) {
            Image image = weatherIcon.getImage();
            Image scaledImage = image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImage);
        }

        // If the weather condition doesn't match any predefined conditions, return null
        return null;
    }
}
