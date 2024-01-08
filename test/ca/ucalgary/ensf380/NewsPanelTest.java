package ca.ucalgary.ensf380;

import org.junit.jupiter.api.Assertions; 
import org.junit.jupiter.api.Test;

/**
 * Test class for NewsPanel.
 * @author group1
 * @version 1.0
 */
public class NewsPanelTest {

    /**
     * Test the NewsPanel constructor with a specific keyword.
     */
    @Test
    public void testNewsPanel() {
        // Arrange
        String keyword = "politics"; // Change the keyword to test with different news
        NewsPanel newsPanel = new NewsPanel(keyword);

        // Act & Assert
        // Ensure the NewsPanel is not null
        Assertions.assertNotNull(newsPanel);

        // Ensure the NewsPanel contains a JLabel
        Assertions.assertEquals(1, newsPanel.getComponentCount());
    }
}
