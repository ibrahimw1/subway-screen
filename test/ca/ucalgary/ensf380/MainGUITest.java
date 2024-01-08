package ca.ucalgary.ensf380;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The MainGUITest class provides unit tests for the MainGUI class.
 *
 * <p>
 * The test methods within this class ensure that the MainGUI initializes correctly 
 * and that its components are appropriately set.
 * </p>
 *
 * @author group1
 * @version 1.0
 */
public class MainGUITest {

    private MainGUI gui;
    
    /**
     * Initializes a new MainGUI instance before each test.
     */
    @BeforeEach
    public void setUp() {
        gui = new MainGUI("524901", "Politics");
    }
    
    /**
     * Test if MainGUI initializes without errors.
     */
    @Test
    public void testMainGUIInitialization() {
        assertNotNull(gui);
    }
    
    /**
     * Test if the main frame title is correctly set.
     */
    @Test
    public void testMainFrameTitle() {
        assertEquals("Subway Information Display", gui.getTitle());
    }
    
    /**
     * Test if the main frame default close operation is set correctly.
     */
    @Test
    public void testDefaultCloseOperation() {
        assertEquals(JFrame.EXIT_ON_CLOSE, gui.getDefaultCloseOperation());
    }

    /**
     * Test if the layout of the main frame is GridBagLayout.
     */
    @Test
    public void testMainFrameLayout() {
        assertTrue(gui.getLayout() instanceof GridBagLayout);
    }

    
}

