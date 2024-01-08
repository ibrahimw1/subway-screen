package ca.ucalgary.ensf380;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;

/**
 * The LoginGUITest class provides unit tests for the LoginGUI class.
 * 
 * <p>
 * This test class aims to verify the functionality of the LoginGUI, ensuring
 * that the initialization and interactions behave as expected.
 * </p>
 * 
 * @author group1
 * @version 1.0
 */
public class LoginGUITest {

    // The instance of LoginGUI to be tested
    private LoginGUI loginGUI;

    /**
     * Set up the necessary resources before each test.
     * In this case, it initializes the LoginGUI instance.
     */
    @BeforeEach
    public void setUp() {
        loginGUI = new LoginGUI();
    }

    /**
     * Clean up resources after each test.
     * Here, it disposes the LoginGUI window to ensure fresh state for each test.
     */
    @AfterEach
    public void tearDown() {
        loginGUI.dispose();
    }

    /**
     * Test to ensure the LoginGUI initializes with the correct title.
     */
    @Test
    public void testLoginGUITitle() {
        assert(loginGUI.getTitle().equals("Subway Login"));
    }

    /**
     * Test to ensure the LoginGUI initializes with the correct size.
     */
    @Test
    public void testLoginGUISize() {
        Dimension expectedSize = new Dimension(300, 200);
        assert(loginGUI.getSize().equals(expectedSize));
    }

    /**
     * Test to ensure that the JTextField for city code is present and editable.
     */
    @Test
    public void testCityCodeField() {
        JTextField cityCodeField = loginGUI.cityCodeField;
        assert(cityCodeField != null);
        assert(cityCodeField.isEditable());
    }

    /**
     * Test to ensure that the JTextField for keyword is present and editable.
     */
    @Test
    public void testKeywordField() {
        JTextField keywordField = loginGUI.keywordField;
        assert(keywordField != null);
        assert(keywordField.isEditable());
    }
}

