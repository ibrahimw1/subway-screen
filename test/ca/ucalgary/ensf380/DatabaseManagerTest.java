package ca.ucalgary.ensf380;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * This class tests the functionality of the DatabaseManager, 
 * specifically its ability to handle ad file paths in the database.
 * <p>
 * The tests focus on the insertion, retrieval, and deletion 
 * of ad file paths within the database.
 * </p>
 * 
 * @author group1
 * @version 1.0
 */
public class DatabaseManagerTest {

    /** An instance of the DatabaseManager to be tested. */
    private static DatabaseManager databaseManager;

    /**
     * Set up the test environment.
     * <p>
     * This method initializes the DatabaseManager and inserts test 
     * ad file paths into the database before the actual tests are run.
     * </p>
     */
    @BeforeAll
    public static void setup() {
        // Initialize the DatabaseManager before running the tests
        databaseManager = new DatabaseManager();
        
        // Insert test ad file paths into the database
        insertTestAdFilePaths();
    }

    /**
     * Clean up the test environment after all tests are completed.
     * <p>
     * This method deletes all the test ad file paths that were 
     * added to the database during the setup phase.
     * </p>
     */
    @AfterAll
    public static void cleanup() {
        // Delete test ad file paths from the database after running the tests
        deleteTestAdFilePaths();
    }

    /**
     * Inserts predefined test ad file paths into the database.
     * <p>
     * This is a helper method for the setup process.
     * </p>
     */
    private static void insertTestAdFilePaths() {
        // Insert mock ad file paths for testing purposes
        // These paths should point to some ad images within the project directory
        for (int i = 1; i <= 10; i++) {
            databaseManager.insertAdFilePathsToDatabase("/ca/ucalgary/ensf380/ad" + i + ".jpg");
        }
    }

    /**
     * Deletes the predefined test ad file paths from the database.
     * <p>
     * This is a helper method to ensure the database returns to its 
     * initial state after the tests are run.
     * </p>
     */
    private static void deleteTestAdFilePaths() {
        // Delete the mock ad file paths post testing
        for (int i = 1; i <= 10; i++) {
            databaseManager.deleteAdFilePath("/ca/ucalgary/ensf380/ad" + i + ".jpg");
        }
    }

    /**
     * Test to verify if the ad file paths are correctly retrieved 
     * from the database.
     */
    @Test
    public void testGetAdFilePaths() {
        // Prepare a list of expected file paths
        ArrayList<String> expectedFilePaths = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            expectedFilePaths.add("/ca/ucalgary/ensf380/ad" + i + ".jpg");
        }

        // Fetch the file paths from the database
        ArrayList<String> retrievedFilePaths = databaseManager.getAdFilePaths();

        // Assert that the retrieved file paths match the expected list
        assertEquals(expectedFilePaths, retrievedFilePaths);
    }
}
