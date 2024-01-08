package ca.ucalgary.ensf380;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.swing.ImageIcon;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the WeatherProcessor class.
 * 
 * @author group1
 * @version 1.0
 */
public class WeatherProcessorTest {

    // Set a sample width and height for testing purposes
    private final int TEST_WIDTH = 50;
    private final int TEST_HEIGHT = 50;

    /**
     * Setup method to prepare any prerequisites before each test.
     */
    @BeforeEach
    public void setup() {
        // Future setup steps, if needed.
    }

    /**
     * Test to check if the correct icon is returned for "clear sky" condition.
     */
    @Test
    public void testClearSkyIcon() {
        ImageIcon icon = WeatherProcessor.getWeatherIcon("clear sky", TEST_WIDTH, TEST_HEIGHT);
        assertNotNull(icon);
        assertEquals(TEST_WIDTH, icon.getIconWidth());
        assertEquals(TEST_HEIGHT, icon.getIconHeight());
    }

    /**
     * Test to check if the correct icon is returned for "few clouds" condition.
     */
    @Test
    public void testFewCloudsIcon() {
        ImageIcon icon = WeatherProcessor.getWeatherIcon("few clouds", TEST_WIDTH, TEST_HEIGHT);
        assertNotNull(icon);
        assertEquals(TEST_WIDTH, icon.getIconWidth());
        assertEquals(TEST_HEIGHT, icon.getIconHeight());
    }

    // Add similar tests for other weather conditions...

    /**
     * Test to check if a null icon is returned for an unrecognized condition.
     */
    @Test
    public void testUnrecognizedCondition() {
        ImageIcon icon = WeatherProcessor.getWeatherIcon("alien invasion", TEST_WIDTH, TEST_HEIGHT);
        assertNull(icon);
    }

    /**
     * Test to check if the icon is correctly scaled.
     */
    @Test
    public void testIconScaling() {
        ImageIcon icon = WeatherProcessor.getWeatherIcon("snow", 100, 150);
        assertNotNull(icon);
        assertEquals(100, icon.getIconWidth());
        assertEquals(150, icon.getIconHeight());
    }
}

