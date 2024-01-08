package ca.ucalgary.ensf380;

import org.junit.jupiter.api.Assertions; 
import org.junit.jupiter.api.Test;

/**
 * Test class for NewsAPI.
 * @author group1
 * @version 1.0
 */
public class NewsAPITest {

    /**
     * Test the getNews method of NewsAPI.
     */
    @Test
    public void testGetNews() {
        // Arrange
        NewsAPI newsAPI = new NewsAPI();
        String keyword = "politics"; // Change the keyword to test with different news

        // Act
        String newsJson = newsAPI.getNews(keyword);

        // Assert
        // Ensure the returned JSON string is not empty or null
        Assertions.assertNotNull(newsJson);
        Assertions.assertFalse(newsJson.isEmpty());
    }
}
