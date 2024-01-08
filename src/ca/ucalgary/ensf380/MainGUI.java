package ca.ucalgary.ensf380;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * The main GUI class holds all the panels for weather, news, advertisements
 * 
 * @author group1
 * @version 1.1
 */
public class MainGUI extends JFrame {
    private JLabel adsLabel = new JLabel(); // New JLabel to display ads
    private DatabaseManager databaseManager;
    // Station Panel (comment out if needed)
    private JLabel stationLabel;
    private Timer stationUpdateTimer;
    private TrainSimulator simulator;

    
    
    /**
     * Constructor initializes the GUI layout, fetches and displays weather, advertisements, and other relevant details.
     * 
     * @param cityCode The code representing the city to fetch weather information.
     * @param keyword The keyword to fetch related news.
     */
    public MainGUI(String cityCode, String keyword) {
        super("Subway Information Display");
        setLayout(new GridBagLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Set the background color for the adPanel
        JPanel adPanel = new JPanel();
        adPanel.setBackground(Color.CYAN);
        GridBagConstraints adPanelConstraints = new GridBagConstraints();
        adPanelConstraints.gridx = 0;
        adPanelConstraints.gridy = 0;
        adPanelConstraints.weightx = 0.65;
        adPanelConstraints.weighty = 0.65;
        adPanelConstraints.fill = GridBagConstraints.BOTH;
        adPanel.setLayout(new BorderLayout());

        // Set the Opaque property of the adsLabel to false to avoid background display
        adsLabel.setOpaque(false);
        adPanel.add(adsLabel, BorderLayout.CENTER); // Add the adsLabel to the adPanel
        add(adPanel, adPanelConstraints);

        // Initialize the database manager
        databaseManager = new DatabaseManager();

        // Insert file paths to the database (if needed)
        AdvertisementManager advertisementManager = insertAdFilePathsToDatabase();

        // Start rotating advertisements
        advertisementManager.startRotatingAdvertisements(adsLabel);

        // Create weather panel
        JPanel timeWeatherPanel = new JPanel();
        timeWeatherPanel.setLayout(new BorderLayout());
        timeWeatherPanel.setBackground(new Color (230,230,230));
        timeWeatherPanel.setPreferredSize(new Dimension(370, 405)); // Adjusted for 40% of 800 width

        // Fetch weather details
        WeatherAPI api = new WeatherAPI();
        WeatherDetails weatherDetails = api.fetchWeather(cityCode);

        // Create weather icon using WeatherProcessor class
        ImageIcon weatherIcon = WeatherProcessor.getWeatherIcon(weatherDetails.getWeatherCondition(), 200, 200);
        JLabel weatherIconLabel = new JLabel(weatherIcon);
        weatherIconLabel.setBorder(new EmptyBorder(0, 0, -30, 0));
        weatherIconLabel.setAlignmentX(CENTER_ALIGNMENT); // Center align
        
        
       
      

        // Create temperature label
        int temperatureCelsius = (int) Math.round(Double.parseDouble(weatherDetails.getTemperature()) - 273.15);
        JLabel temperatureLabel = new JLabel(String.format("%d°C", temperatureCelsius), SwingConstants.CENTER);
        temperatureLabel.setForeground(Color.WHITE); // Text Color White
        temperatureLabel.setAlignmentX(CENTER_ALIGNMENT); // Center align
        temperatureLabel.setFont(new Font(temperatureLabel.getFont().getName(), Font.BOLD, 45));

        // Create humidity label
        JLabel humidityLabel = new JLabel("Humidity: " + weatherDetails.getHumidity() + "%");
        humidityLabel.setHorizontalAlignment(SwingConstants.CENTER);
        humidityLabel.setAlignmentX(CENTER_ALIGNMENT); // Center align
        humidityLabel.setForeground(Color.WHITE);
        humidityLabel.setFont(new Font(humidityLabel.getFont().getName(), Font.PLAIN, 15)); // Adjust the font size here

        // Create wind speed label
        JLabel windSpeedLabel = new JLabel("Wind Speed: " + weatherDetails.getWindSpeed() + " m/s");
        windSpeedLabel.setHorizontalAlignment(SwingConstants.CENTER);
        windSpeedLabel.setAlignmentX(CENTER_ALIGNMENT); // Center align
        windSpeedLabel.setForeground(Color.WHITE);
        windSpeedLabel.setFont(new Font(windSpeedLabel.getFont().getName(), Font.PLAIN, 15)); // Adjust the font size here

        // Create date label
        Date date = new Date(Long.parseLong(weatherDetails.getDate()) * 1000);
        String timeString = new SimpleDateFormat("HH:mm:ss").format(date);
        String dateString = new SimpleDateFormat("MM/dd/yyyy").format(date);
        String fullDateString = "<html><div style='text-align: center;'><span style='font-size: 30px; font-weight: bold;'>"
                + timeString + "</span><br/><span style='font-size: 14px;'>" + dateString + "</span></div></html>"; // Font size for date reduced to 18px
        JLabel dateLabel = new JLabel(fullDateString);
        dateLabel.setHorizontalAlignment(SwingConstants.CENTER);
        dateLabel.setForeground(Color.decode("#3A4660")); // Navy Dark Blue

        // Create middle and bottom panel
        JPanel middleBottomPanel = new JPanel();
        middleBottomPanel.setLayout(new BoxLayout(middleBottomPanel, BoxLayout.Y_AXIS));
        middleBottomPanel.setBackground(Color.decode("#3A4660"));

        // Add components to the middle and bottom panel
        middleBottomPanel.add(weatherIconLabel);
        middleBottomPanel.add(Box.createVerticalStrut(20));
        middleBottomPanel.add(temperatureLabel);
        middleBottomPanel.add(Box.createVerticalStrut(10));
        middleBottomPanel.add(humidityLabel);
        middleBottomPanel.add(windSpeedLabel);

        // Add components to the time and weather panel
        timeWeatherPanel.add(dateLabel, BorderLayout.NORTH);
        timeWeatherPanel.add(middleBottomPanel, BorderLayout.CENTER);

        GridBagConstraints timeWeatherPanelConstraints = new GridBagConstraints();
        timeWeatherPanelConstraints.gridx = 1;
        timeWeatherPanelConstraints.gridy = 0;
        timeWeatherPanelConstraints.weightx = 0.4; // Adjusted weight for 40%
        timeWeatherPanelConstraints.fill = GridBagConstraints.BOTH;
        add(timeWeatherPanel, timeWeatherPanelConstraints);

        // Create news panel
        JPanel newsPanel = new NewsPanel(keyword); // Use the custom NewsPanel class
        GridBagConstraints newsPanelConstraints = new GridBagConstraints();
        newsPanelConstraints.gridx = 0;
        newsPanelConstraints.gridy = 1;
        newsPanelConstraints.gridwidth = 2;
        newsPanelConstraints.fill = GridBagConstraints.BOTH;
        add(newsPanel, newsPanelConstraints);

        // Station panel
        JPanel stationPanel = new JPanel();
        stationPanel.setBackground(Color.ORANGE);
        stationPanel.setPreferredSize(new Dimension(1030, 110)); // Set preferred height
        GridBagConstraints stationPanelConstraints = new GridBagConstraints();
        stationPanelConstraints.gridx = 0;
        stationPanelConstraints.gridy = 2;
        stationPanelConstraints.gridwidth = 2;
        stationPanelConstraints.fill = GridBagConstraints.BOTH;
        add(stationPanel, stationPanelConstraints);
        
//        simulator = new TrainSimulator();
//
//        stationLabel = new JLabel();
//        stationLabel.setHorizontalAlignment(SwingConstants.CENTER);
//        stationPanel.add(stationLabel);
//
//        stationUpdateTimer = new Timer(15000, new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                stationLabel.setText(simulator.getUpdatedStationInfo());
//            }
//        });
//        stationUpdateTimer.start();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    
    
    /**
     * Inserts advertisement file paths into the database and returns an instance of the AdvertisementManager.
     * 
     * @return An initialized AdvertisementManager instance.
     */
    private AdvertisementManager insertAdFilePathsToDatabase() {
        // Assuming you have a list of file paths in an ArrayList<String> called adFilePaths
        ArrayList<String> adFilePaths = new ArrayList<>();
        adFilePaths.add("/ca/ucalgary/ensf380/ad1.jpg");
        adFilePaths.add("/ca/ucalgary/ensf380/ad2.jpg");
        adFilePaths.add("/ca/ucalgary/ensf380/ad3.jpg");
        adFilePaths.add("/ca/ucalgary/ensf380/ad4.jpg");
        adFilePaths.add("/ca/ucalgary/ensf380/ad5.jpg");
        adFilePaths.add("/ca/ucalgary/ensf380/ad6.jpg");
        adFilePaths.add("/ca/ucalgary/ensf380/ad7.jpg");
        adFilePaths.add("/ca/ucalgary/ensf380/ad8.jpg");
        adFilePaths.add("/ca/ucalgary/ensf380/ad9.jpg");
        adFilePaths.add("/ca/ucalgary/ensf380/ad10.jpg");

        // Insert the file paths to the database
        return new AdvertisementManager(adFilePaths);
    }

    
    
    /**
     * The main entry point for the application.
     * 
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        //MainGUI gui = new MainGUI("524901", "Politics"); // Replace "keyword" with a relevant value or get it from args
        LoginGUI loginGUI = new LoginGUI();
    }
}


     
/*      
    public static void main(String[] args) {
        // Replace these with your desired city code and keyword
        String cityCode = "524901";
        String keyword = "Politics";

        SwingUtilities.invokeLater(() -> new MainGUI(cityCode, keyword));
    }
    
    
    
}
*/
  
  
/*
   public static void main(String[] args) {
        if (args.length < 2) {
           System.out.println("Please provide city code and keyword as arguments.");
           return;
        }
        String cityCode = args[0];
        String keyword = args[1];
        MainGUI gui = new MainGUI(cityCode, keyword);

    }
}
*/

  
 
 

