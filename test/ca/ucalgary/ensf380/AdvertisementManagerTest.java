package ca.ucalgary.ensf380;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.util.List;

/**
 * Test class for AdvertisementManager.
 * @author group1
 * @version 1.0
 */
public class AdvertisementManagerTest {
    private static AdvertisementManager advertisementManager;
    private static final String AD_DIR = "/ca/ucalgary/ensf380/";

    @BeforeAll
    public static void setUp() {
        // Set up the AdvertisementManager with a list of advertisement file paths
        List<String> adFilePaths = List.of(
                AD_DIR + "ad1.jpg",
                AD_DIR + "ad2.jpg",
                AD_DIR + "ad3.jpg",
                AD_DIR + "ad4.jpg",
                AD_DIR + "ad5.jpg",
                AD_DIR + "ad6.jpg",
                AD_DIR + "ad7.jpg",
                AD_DIR + "ad8.jpg",
                AD_DIR + "ad9.jpg",
                AD_DIR + "ad10.jpg"
        );
        advertisementManager = new AdvertisementManager(adFilePaths);
    }

    /**
     * Test startRotatingAdvertisements method of AdvertisementManager.
     */
    @Test
    public void testStartRotatingAdvertisements() {
        // Create a JLabel for displaying advertisements
        JLabel advertisementLabel = new JLabel();

        // Act
        advertisementManager.startRotatingAdvertisements(advertisementLabel);

        // Assert
        // Ensure the advertisementLabel has a non-null icon after starting rotation
        Assertions.assertNotNull(advertisementLabel.getIcon());

        // Sleep for 30 seconds to allow rotation to occur multiple times
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Ensure the advertisementLabel has a different icon after rotation
        Assertions.assertNotEquals(advertisementManager.loadImageIcon(AD_DIR + "ad1.jpg"), advertisementLabel.getIcon());
    }
}
