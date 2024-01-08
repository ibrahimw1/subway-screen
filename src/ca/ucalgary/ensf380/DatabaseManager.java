/**
@author group1 <a  

@version 1.1
@since 1.0
*/





package ca.ucalgary.ensf380;

import java.sql.*;    
import java.util.ArrayList;
import java.io.File;
import java.util.List;
import java.net.URL;

/**
 * The DatabaseManager class is responsible for managing the database and handling operations related to advertisements.
 */
public class DatabaseManager {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/subway_advertisement_db";
    private static final String DB_USERNAME = "new_user";
    private static final String DB_PASSWORD = "new_password"; 
    private Connection conn;

    /**
     * Constructs a new DatabaseManager object and establishes a connection to the database.
     */
    public DatabaseManager() {
        try {
            // Establish a connection to the database
            conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            // Create a Statement object to execute SQL queries
            Statement statement = conn.createStatement();

            // SQL query to check if the advertisements table already exists
            String checkTableQuery = "SHOW TABLES LIKE 'advertisements'";

            // Execute the query to check if the table exists
            ResultSet resultSet = statement.executeQuery(checkTableQuery);

            // If the table does not exist, create it
            if (!resultSet.next()) {
                // SQL query to create the advertisements table
                String createTableQuery = "CREATE TABLE advertisements ("
                        + "id INT AUTO_INCREMENT PRIMARY KEY,"
                        + "text_content TEXT NOT NULL,"
                        + "image_path VARCHAR(255) NOT NULL"
                        + ")";

                // Execute the query to create the table
                statement.executeUpdate(createTableQuery);

                System.out.println("Advertisements table created successfully.");
            } else {
                System.out.println("Advertisements table already exists.");
            }

            // Close the resources
            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Inserts the given file path to the advertisements table in the database.
     * @param filePath The file path to be inserted.
     */
    public void insertAdFilePathsToDatabase(String filePath) {
        // Get the URL of the resource
        URL resourceUrl = DatabaseManager.class.getResource(filePath);

        // Convert the URL to a file path
        String absoluteFilePath = new File(resourceUrl.getPath()).getAbsolutePath();

        String sql = "INSERT INTO advertisements (image_path) VALUES (?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, absoluteFilePath);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves a list of advertisement file paths from the advertisements table in the database.
     * @return ArrayList of String representing advertisement file paths.
     */
    public ArrayList<String> getAdFilePaths() {
        ArrayList<String> adFilePaths = new ArrayList<>();

        try (Statement statement = conn.createStatement()) {
            String sql = "SELECT image_path FROM advertisements";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String filePath = resultSet.getString("image_path");
                adFilePaths.add(filePath);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return adFilePaths;
    }

    
    /**
     * Deletes the given file path from the advertisements table in the database.
     * @param filePath The file path to be deleted.
     */
    public void deleteAdFilePath(String filePath) {
        String sql = "DELETE FROM advertisements WHERE image_path = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, filePath);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    
    public static void main(String[] args) {
        new DatabaseManager();
    }
}
