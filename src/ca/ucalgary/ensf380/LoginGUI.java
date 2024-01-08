package ca.ucalgary.ensf380;

import javax.swing.*;
import java.awt.*;

/**
 * The LoginGUI class provides a graphical user interface for the login process.
 * 
 * <p>
 * Users are prompted to input a city code and a keyword before launching the main GUI.
 * For more information, check the Javadocs in:
 * C:\Users\tahme\eclipse-workspace\FinalProject\doc
 * </p>
 * 
 * @author group1
 * @version 1.0
 */
public class LoginGUI extends JFrame {

    /** Text field to input the city code. */
    JTextField cityCodeField;
    
    /** Text field to input the keyword. */
    JTextField keywordField;

    /**
     * Constructor initializes the Login GUI layout and elements.
     */
    public LoginGUI() {
        // Set the window title
        super("Subway Login");

        // Configure the frame settings
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 200);
        setLayout(new GridLayout(3, 2));

        // Create label and text field for city code
        JLabel cityCodeLabel = new JLabel("City Code:");
        cityCodeField = new JTextField();

        // Create label and text field for keyword
        JLabel keywordLabel = new JLabel("Keyword:");
        keywordField = new JTextField();

        // Create login button and set its action listener
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> loginButtonClicked());

        // Add components to the frame
        add(cityCodeLabel);
        add(cityCodeField);
        add(keywordLabel);
        add(keywordField);
        add(loginButton);

        // Make the frame visible
        setVisible(true);
    }

    /**
     * This method is invoked when the login button is clicked.
     * It checks the user input and proceeds with the creation of the MainGUI or displays an error message.
     */
    private void loginButtonClicked() {
        // Fetch inputs from text fields
        String cityCode = cityCodeField.getText();
        String keyword = keywordField.getText();

        // Check for empty input fields
        if (cityCode.isEmpty() || keyword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "City code and keyword should not be empty.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            // Initialize the main GUI with the user inputs and close the login GUI
            MainGUI mainGUI = new MainGUI(cityCode, keyword);
            dispose();
        }
    }
}
