package com.academic.querysystem;

import javax.swing.*;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Main {
    // MySQL connection setup
    private static Connection connect() {
        Connection connection = null;
        try {
            String url = "jdbc:mysql://localhost:3306/academicq"; 
            String user = "root"; 
            String password = "Root@123"; 
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::showOptionPage);
    }

    // Method to show option page for Login or Sign Up
    public static void showOptionPage() {
        JFrame frame = createFrame("Academic Query System", new Color(240, 240, 240));
        ImageIcon welcomeImage = new ImageIcon("C:\\Users\\RAMESH\\Desktop\\academicq\\src\\img\\welcome.jpg"); // Replace with the correct path
    JLabel imageLabel = new JLabel(welcomeImage);
    imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align the image
    frame.add(imageLabel); // Light gray background

        JButton loginButton = createButton("Login");
        JButton signupButton = createButton("Sign Up");

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Vertical box layout
        panel.add(Box.createRigidArea(new Dimension(0, 50))); // Space at the top
        panel.add(loginButton);
        panel.add(Box.createRigidArea(new Dimension(0, 20))); // Space between buttons
        panel.add(signupButton);
        panel.add(Box.createRigidArea(new Dimension(0, 50))); // Space at the bottom

        frame.add(panel);
        frame.setVisible(true);

        loginButton.addActionListener(e -> {
            frame.dispose();
            loginPage(); // Open login page
        });

        signupButton.addActionListener(e -> {
            frame.dispose();
            signUpPage(); // Open sign-up page
        });
    }

    // Method for login page with reduced text box size
    public static void loginPage() {
        JFrame frame = createFrame("Login", Color.WHITE); // White background
        ImageIcon loginImage = new ImageIcon("C:\\Users\\RAMESH\\Desktop\\academicq\\src\\img\\login.jpg"); // Replace with the correct path
        JLabel imageLabel = new JLabel(loginImage);
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align the image
        frame.add(imageLabel);
        
        JLabel userLabel = new JLabel("Username:");
        JTextField userField = new JTextField(20);
        userField.setMaximumSize(new Dimension(200, 30)); // Reduced size
        JLabel passLabel = new JLabel("Password:");
        JPasswordField passField = new JPasswordField(20);
        passField.setMaximumSize(new Dimension(200, 30)); // Reduced size
        JButton loginButton = createButton("Login");
    
        addComponentsToFrame(frame, userLabel, userField, passLabel, passField, loginButton);
    
        loginButton.addActionListener(e -> {
            String username = userField.getText();
            String password = new String(passField.getPassword());
    
            if (validateLogin(username, password)) {
                frame.dispose();  // Close the login window
                queryPage();  // Open the query submission page
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid credentials!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    
        frame.setVisible(true);
    }
    

    // Method for signup page
    public static void signUpPage() {
        JFrame frame = createFrame("Sign Up", Color.WHITE); // White background
        ImageIcon sImage = new ImageIcon("C:\\Users\\RAMESH\\Desktop\\academicq\\src\\img\\signup.jpg"); // Replace with the correct path
        JLabel imageLabel = new JLabel(sImage);
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align the image
        frame.add(imageLabel);
        

        JLabel userLabel = new JLabel("Username:");
        JTextField userField = new JTextField(20);
        userField.setMaximumSize(new Dimension(200, 30)); // Reduced size
        JLabel passLabel = new JLabel("Password:");
        JPasswordField passField = new JPasswordField(20);
        passField.setMaximumSize(new Dimension(200, 30)); // Reduced size
        JButton signUpButton = createButton("Sign Up");

        addComponentsToFrame(frame, userLabel, userField, passLabel, passField, signUpButton);

        signUpButton.addActionListener(e -> {
            String username = userField.getText();
            String password = new String(passField.getPassword());

            if (createNewUser(username, password)) {
                JOptionPane.showMessageDialog(frame, "Sign-up successful! Please log in.", "Success", JOptionPane.INFORMATION_MESSAGE);
                frame.dispose();
                loginPage();  // Redirect to login page after successful sign-up
            } else {
                JOptionPane.showMessageDialog(frame, "Username already exists!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        frame.setVisible(true);
    }

    // Method to validate login credentials from MySQL database
    public static boolean validateLogin(String username, String password) {
        boolean valid = false;
        try (Connection connection = connect()) {
            if (connection != null) {
                String sql = "SELECT * FROM login WHERE username = ? AND password = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, username);
                statement.setString(2, password);

                ResultSet resultSet = statement.executeQuery();
                valid = resultSet.next(); // If a record exists, login is valid
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return valid;
    }

    // Method to create a new user in the database
    public static boolean createNewUser(String username, String password) {
        boolean created = false;
        try (Connection connection = connect()) {
            if (connection != null) {
                String sql = "INSERT INTO login (username, password) VALUES (?, ?)";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, username);
                statement.setString(2, password);

                statement.executeUpdate();
                created = true;  // User created successfully
            }
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {  // Duplicate entry error (username exists)
                System.out.println("Username already exists!");
            } else {
                e.printStackTrace();
            }
        }
        return created;
    }

    // Query submission page
    public static void queryPage() {
        JFrame frame = createFrame("Academic Query Management System", new Color(240, 240, 240)); // Light gray background

        ImageIcon qImage = new ImageIcon("C:\\Users\\RAMESH\\Desktop\\academicq\\src\\img\\q.jpg"); // Replace with the correct path
        JLabel imageLabel = new JLabel(qImage);
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align the image
        frame.add(imageLabel);
        
        // Welcome message
        JLabel welcomeLabel = new JLabel("Welcome to the Automated Academic Query Manaement System!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(welcomeLabel);
        frame.add(Box.createRigidArea(new Dimension(0, 20))); // Space below welcome message

        JLabel label = new JLabel("Feel free to add your queries and get your answers:");
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(label);
        frame.add(Box.createRigidArea(new Dimension(0, 10))); // Space below label

        JTextField queryField = new JTextField(30); // Consistent textbox size
        queryField.setMaximumSize(queryField.getPreferredSize()); // Maintain size
        frame.add(queryField);
        frame.add(Box.createRigidArea(new Dimension(0, 10))); // Space below text box

        JButton submitButton = createButton("Submit");
        frame.add(submitButton);
        frame.add(Box.createRigidArea(new Dimension(0, 20))); // Space at the bottom

        submitButton.addActionListener(e -> {
            String query = queryField.getText().trim(); // Remove leading/trailing whitespace

            // Validate query input
            if (query.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter a query!", "Empty Query", JOptionPane.WARNING_MESSAGE);
                return; // Exit if query is empty
            }

            submitQuery(query, frame);
            queryField.setText(""); // Clear the input field
            triggerDroolsResponse(query, frame);  // Trigger Drools for reasoning
        });

        frame.setVisible(true);
    }

    // Method to insert query into the database
    private static void submitQuery(String queryText, JFrame frame) {
        UserQuery query = new UserQuery(queryText); // Create a Query object
        try (Connection connection = connect()) {
            if (connection != null) {
                String sql = "INSERT INTO queries (query_text) VALUES (?)";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, query.getQueryText());

                statement.executeUpdate();
                // Custom success icon
ImageIcon successIcon = new ImageIcon("C:\\Users\\RAMESH\\Desktop\\academicq\\src\\img\\response.jpg");

// HTML styled success message
String successMessage = "<html><body style='font-family:sans-serif; font-size:12px;'>"
        + "<h2 style='color:green;'>Success</h2>"
        + "<p style='color:black;'>Query submitted successfully!</p>"
        + "</body></html>";

// Display the dialog
JOptionPane.showMessageDialog(
        frame, 
        successMessage, 
        "Success", 
        JOptionPane.INFORMATION_MESSAGE, 
        successIcon
);

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "Failed to submit the query!", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    // Method to trigger Drools for reasoning
    private static void triggerDroolsResponse(String query, JFrame frame) {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("ksession-rules");

        try {
            UserQuery userQuery = new UserQuery(query); // Create a new UserQuery object
            kieSession.insert(userQuery); // Insert the UserQuery object into the KieSession
            kieSession.fireAllRules(); // Execute the rules

            // After firing rules, retrieve the response
            String response = userQuery.getResponse();
            if (response == null) {
                response = "No response generated."; // Fallback message if no response was set
            }
            // Custom icon
ImageIcon icon = new ImageIcon("C:\\Users\\RAMESH\\Desktop\\academicq\\src\\img\\response.jpg");

// HTML styled message
String styledMessage = "<html><body style='font-family:sans-serif; font-size:12px;'>"
        + "<h3 style='color:blue;'>Drools Response</h3>"
        + "<p style='color:black;'>" + response + "</p>"
        + "</body></html>";

// Display the dialog
JOptionPane.showMessageDialog(
        frame, 
        styledMessage, 
        "Response", 
        JOptionPane.INFORMATION_MESSAGE, 
        icon
);

        } finally {
            kieSession.dispose(); // Clean up KieSession
        }
    }

    // Method to create a frame with a specified title and background color
    private static JFrame createFrame(String title, Color backgroundColor) {
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setResizable(false);
        frame.getContentPane().setBackground(backgroundColor);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS)); // Vertical box layout
        frame.setLocationRelativeTo(null); // Center on screen
        return frame;
    }

    // Method to create a styled button
    private static JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align button
        button.setPreferredSize(new Dimension(150, 40));
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(new Color(100, 149, 237)); // Cornflower blue background
        button.setForeground(Color.WHITE); // White text color
        button.setFocusPainted(false); // Remove focus outline
        button.setBorderPainted(false); // Remove border
        button.setOpaque(true);
        
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(70, 130, 180)); // Darker blue on hover
            }
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(100, 149, 237)); // Revert to original color
            }
        });
        
        return button;
    }

    // Helper method to add multiple components to a frame
    private static void addComponentsToFrame(JFrame frame, JComponent... components) {
        for (JComponent component : components) {
            component.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align components
            frame.add(component);
            frame.add(Box.createRigidArea(new Dimension(0, 10))); // Space between components
        }
    }
}
