
package ca.ucalgary.ensf380;

import javax.swing.*;
import java.io.InputStream;
import java.util.List;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 * The AdvertisementManager class is responsible for managing advertisements and rotating them in a given time interval.
 * @author group1
 * @version 1.0
 */
public class AdvertisementManager {
    private List<String> adFilePaths;
    private int currentAdIndex = 0;

    /**
     * Constructs a new AdvertisementManager object with the given list of advertisement file paths.
     * @param adFilePaths List of advertisement file paths.
     */
    public AdvertisementManager(List<String> adFilePaths) {
        this.adFilePaths = adFilePaths;
    }

    /**
     * Starts rotating advertisements on the given label every 10 seconds.
     * @param advertisementLabel The label to display advertisements.
     */
    public void startRotatingAdvertisements(JLabel advertisementLabel) {
        if (adFilePaths.isEmpty()) {
            // No advertisements found, display a default message or image
            advertisementLabel.setText("No advertisements available.");
            return;
        }
        // Start the timer to rotate the advertisements every 10 seconds
        Timer timer = new Timer(10000, e -> displayNextAdvertisement(advertisementLabel));
        timer.start();
        // Display the first advertisement
        displayNextAdvertisement(advertisementLabel);
    }

    /**
     * Displays the next advertisement on the given label.
     * @param advertisementLabel The label to display the advertisement.
     */
    private void displayNextAdvertisement(JLabel advertisementLabel) {
        String filePath = adFilePaths.get(currentAdIndex);
        ImageIcon imageIcon = loadImageIcon(filePath);
        advertisementLabel.setIcon(imageIcon);
        advertisementLabel.setSize(imageIcon.getIconWidth(), imageIcon.getIconHeight());
        // Move to the next advertisement or loop back to the beginning
        currentAdIndex = (currentAdIndex + 1) % adFilePaths.size();
    }

    /**
     * Loads the image icon from the given file path.
     * @param filePath The file path of the image.
     * @return ImageIcon object representing the loaded image.
     */
    public ImageIcon loadImageIcon(String filePath) {
        try {
            InputStream inputStream = getClass().getResourceAsStream(filePath);
            if (inputStream != null) {
                BufferedImage bufferedImage = ImageIO.read(inputStream);
                inputStream.close();
                return new ImageIcon(bufferedImage);
            } else {
                System.out.println("Error loading advertisement image: " + filePath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Inserts the advertisement file paths to the database using the DatabaseManager class.
     */
    public void insertAdFilePathsToDatabase() {
        DatabaseManager databaseManager = new DatabaseManager();
        for (String filePath : adFilePaths) {
            databaseManager.insertAdFilePathsToDatabase(filePath);
        }
    }
}
