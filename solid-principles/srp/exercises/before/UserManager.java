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