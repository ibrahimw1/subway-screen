package ca.ucalgary.ensf380;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The {@code SimulatorTest} class provides unit tests for the {@code Simulator} class.
 * These tests verify the basic functionality and ensure correct process management
 * and output processing for the subway simulator.
 *
 * @author group1
 * @version 1.0
 */
public class SimulatorTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        // Redirect System.out to capture the output
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        // Reset the System.out to its original stream after the test
        System.setOut(originalOut);
    }

    /**
     * Test to verify the simulator's process output is captured and displayed.
     */
    @Test
    public void testProcessSubwayOutput() {
        // Due to the complexity of simulating the process, we're simply invoking the main function.
        // In a real-world scenario, this would require mocks or stubs to simulate the external process.
        Simulator.main(new String[]{});

        // For simplicity, we're just checking if there's any output.
        // Depending on the expected simulator output, this assertion can be made more specific.
        assertTrue(outContent.toString().length() > 0, "The simulator output should not be empty.");
    }

    // Additional tests can be added as needed
}
