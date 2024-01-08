package ca.ucalgary.ensf380;

import org.json.JSONArray;  
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The NewsPanel class displays a scrolling news ticker with articles related to a specific keyword.
 * @author group1
 * @version 1.0
 */
public class NewsPanel extends JPanel {

    private JLabel newsLabel;
    private StringBuilder newsData;
    private Timer newsTimer;

    /**
     * Constructs a new NewsPanel with the given keyword to fetch news articles.
     *
     * @param keyword The keyword to search for news articles.
     */
    public NewsPanel(String keyword) {
        // Set the layout of the NewsPanel to BorderLayout
        setLayout(new BorderLayout());

        // Set the background color of the NewsPanel to a light blue color
        setBackground(new Color(100, 150, 200));

        // Initialize a StringBuilder to store the news data
        newsData = new StringBuilder();

        // Create an instance of NewsAPI to fetch news data from the API
        NewsAPI newsAPI = new NewsAPI();
        try {
            // Fetch news data in JSON format for the specified keyword
            String json = newsAPI.getNews(keyword);

            // Create a JSONObject to parse the JSON response
            JSONObject jsonObject = new JSONObject(json);

            // Get the "articles" JSONArray from the JSONObject
            JSONArray articles = jsonObject.getJSONArray("articles");

            // Iterate through the articles and extract the "title" of each article
            for (int i = 0; i < articles.length(); i++) {
                String title = articles.getJSONObject(i).getString("title");
                // Append the article title to the newsData StringBuilder with spacing
                newsData.append(title).append("                    |                    ");
            }
        } catch (Exception e) {
            // If there's an exception during the API call or parsing, handle the error
            // and display a "Failed to fetch news" message
            newsData.append("Failed to fetch news");
            e.printStackTrace();
        }

        // Create a new JLabel for displaying the news ticker
        newsLabel = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                // Set the text color of the JLabel to white
                g.setColor(Color.WHITE);
                // Draw the text manually to prevent truncation and center it vertically
                g.drawString(getText(), 0, getHeight() / 2 + g.getFontMetrics().getAscent() / 2);
            }
        };

        // Set the text color of the newsLabel to white
        newsLabel.setForeground(Color.WHITE);

        // Set the preferred size of the newsLabel to a width of 1000 and height of 50 pixels
        newsLabel.setPreferredSize(new Dimension(1000, 50));

        // Set the font of the newsLabel to bold Arial with a size of 24
        newsLabel.setFont(new Font("Arial", Font.BOLD, 24));

        // Set the text of the newsLabel to the content stored in the newsData StringBuilder
        newsLabel.setText(newsData.toString());

        // Add the newsLabel to the center of the NewsPanel
        add(newsLabel, BorderLayout.CENTER);

        // Create a Timer to scroll the news ticker from left to right
        newsTimer = new Timer();

        // Schedule the TimerTask to run every 80 milliseconds (scroll speed)
        newsTimer.schedule(new TimerTask() {
            private int scrollPosition = 0;

            @Override
            public void run() {
                // Shift the news text by one character to the right to create the scrolling effect
                scrollPosition = (scrollPosition + 1) % newsData.length();
                String scrolledText = newsData.substring(scrollPosition) + newsData.substring(0, scrollPosition);
                // Update the text of the newsLabel with the scrolledText
                newsLabel.setText(scrolledText);
            }
        }, 0, 80);
    }
}
