Collecting workspace information# Single Responsibility Principle Exercise

## Problem Statement

### Background
The Single Responsibility Principle (SRP) states that a class should have only one reason to change - in other words, a class should have only one job or purpose in the software.

### Your Task
Below is a `UserManager` class that violates the SRP by handling multiple responsibilities. Your task is to refactor this code to adhere to the SRP by:

1. Identifying the different responsibilities in the `UserManager` class
2. Separating these responsibilities into different classes
3. Ensuring each class has only a single responsibility
4. Maintaining the same functionality of the original code

## Code that Violates SRP

```java
// This class violates SRP by handling multiple responsibilities
public class UserManager {
    private String connectionString = "jdbc:mysql://localhost:3306/userdb";
    
    public User findUser(String userId) {
        // Connect to database
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(connectionString);
            // Query to find user
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
            stmt.setString(1, userId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getString("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                return user;
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            logError("Database error when finding user: " + e.getMessage());
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException e) {
                logError("Error closing connection: " + e.getMessage());
            }
        }
        return null;
    }
    
    public boolean saveUser(User user) {
        // Connect to database
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(connectionString);
            // Query to save user
            PreparedStatement stmt = connection.prepareStatement(
                "INSERT INTO users (id, name, email) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE name = ?, email = ?");
            stmt.setString(1, user.getId());
            stmt.setString(2, user.getName());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getName());
            stmt.setString(5, user.getEmail());
            
            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            logError("Database error when saving user: " + e.getMessage());
            return false;
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException e) {
                logError("Error closing connection: " + e.getMessage());
            }
        }
    }
    
    public void sendWelcomeEmail(User user) {
        try {
            // Set up mail properties
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            
            // Create session
            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("admin@example.com", "password");
                }
            });
            
            // Create message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("admin@example.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getEmail()));
            message.setSubject("Welcome to our service");
            message.setText("Dear " + user.getName() + ",\n\nWelcome to our service!");
            
            // Send message
            Transport.send(message);
            System.out.println("Welcome email sent to " + user.getEmail());
        } catch (MessagingException e) {
            System.out.println("Error sending email: " + e.getMessage());
            logError("Error sending welcome email to " + user.getEmail() + ": " + e.getMessage());
        }
    }
    
    public void generateUserReport(List<User> users, String format) {
        if ("CSV".equalsIgnoreCase(format)) {
            try (FileWriter writer = new FileWriter("users_report.csv")) {
                writer.write("ID,Name,Email\n");
                for (User user : users) {
                    writer.write(user.getId() + "," + user.getName() + "," + user.getEmail() + "\n");
                }
                System.out.println("CSV report generated successfully");
            } catch (IOException e) {
                System.out.println("Error generating CSV report: " + e.getMessage());
                logError("Error generating CSV report: " + e.getMessage());
            }
        } else if ("JSON".equalsIgnoreCase(format)) {
            try (FileWriter writer = new FileWriter("users_report.json")) {
                StringBuilder json = new StringBuilder("[\n");
                for (int i = 0; i < users.size(); i++) {
                    User user = users.get(i);
                    json.append("  {\n");
                    json.append("    \"id\": \"").append(user.getId()).append("\",\n");
                    json.append("    \"name\": \"").append(user.getName()).append("\",\n");
                    json.append("    \"email\": \"").append(user.getEmail()).append("\"\n");
                    json.append("  }");
                    if (i < users.size() - 1) {
                        json.append(",");
                    }
                    json.append("\n");
                }
                json.append("]\n");
                writer.write(json.toString());
                System.out.println("JSON report generated successfully");
            } catch (IOException e) {
                System.out.println("Error generating JSON report: " + e.getMessage());
                logError("Error generating JSON report: " + e.getMessage());
            }
        }
    }
    
    private void logError(String errorMessage) {
        try (FileWriter writer = new FileWriter("error_log.txt", true)) {
            writer.write(new Date() + ": " + errorMessage + "\n");
        } catch (IOException e) {
            System.out.println("Error writing to log file: " + e.getMessage());
        }
    }
}

// Simple User class
class User {
    private String id;
    private String name;
    private String email;
    
    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
```

## Testing Your Solution

To test your solution, create a `SRPTest` class that verifies each responsibility works correctly:

```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.util.Arrays;
import java.util.List;

public class SRPTest {
    
    @Test
    public void testUserPersistence() {
        // Create test user
        User user = new User();
        user.setId("test123");
        user.setName("Test User");
        user.setEmail("test@example.com");
        
        // Your implementation should provide a way to save and find users
        // For example, using a UserRepository class
        UserRepository repository = new UserRepository();
        
        // Test save functionality
        boolean saveResult = repository.saveUser(user);
        assertTrue(saveResult, "User should be saved successfully");
        
        // Test find functionality
        User foundUser = repository.findUser("test123");
        assertNotNull(foundUser, "User should be found");
        assertEquals("Test User", foundUser.getName(), "User name should match");
        assertEquals("test@example.com", foundUser.getEmail(), "User email should match");
    }
    
    @Test
    public void testEmailSending() {
        // Create test user
        User user = new User();
        user.setId("test123");
        user.setName("Test User");
        user.setEmail("test@example.com");
        
        // Your implementation should provide a way to send emails
        // For example, using an EmailService class
        EmailService emailService = new EmailService();
        
        // Test email sending (in a real test, you would use a mock SMTP server)
        boolean result = emailService.sendWelcomeEmail(user);
        assertTrue(result, "Email should be sent successfully");
    }
    
    @Test
    public void testReportGeneration() {
        // Create test users
        User user1 = new User();
        user1.setId("user1");
        user1.setName("User One");
        user1.setEmail("user1@example.com");
        
        User user2 = new User();
        user2.setId("user2");
        user2.setName("User Two");
        user2.setEmail("user2@example.com");
        
        List<User> users = Arrays.asList(user1, user2);
        
        // Your implementation should provide a way to generate reports
        // For example, using a ReportGenerator class
        ReportGenerator reportGenerator = new ReportGenerator();
        
        // Test CSV report generation
        boolean csvResult = reportGenerator.generateReport(users, "CSV");
        assertTrue(csvResult, "CSV report should be generated successfully");
        assertTrue(new File("users_report.csv").exists(), "CSV file should exist");
        
        // Test JSON report generation
        boolean jsonResult = reportGenerator.generateReport(users, "JSON");
        assertTrue(jsonResult, "JSON report should be generated successfully");
        assertTrue(new File("users_report.json").exists(), "JSON file should exist");
    }
    
    @Test
    public void testErrorLogging() {
        // Your implementation should provide a way to log errors
        // For example, using a Logger class
        Logger logger = new Logger();
        
        // Test logging
        boolean result = logger.logError("Test error message");
        assertTrue(result, "Error should be logged successfully");
        assertTrue(new File("error_log.txt").exists(), "Log file should exist");
    }
}
```

## Hints

1. Identify all the different responsibilities in the `UserManager` class
2. Create separate classes for each responsibility (e.g., `UserRepository`, `EmailService`, `ReportGenerator`, `Logger`)
3. Make sure each class has a single responsibility
4. Update the tests to work with your refactored classes

Good luck!

Similar code found with 1 license type