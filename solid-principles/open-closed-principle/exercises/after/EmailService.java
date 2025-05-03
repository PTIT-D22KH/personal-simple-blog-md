public class EmailService {
    private LogService logService;
    public EmailService(LogService logService) {
        this.logService = logService;
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
            logService.logError("Error sending welcome email to " + user.getEmail() + ": " + e.getMessage());
        }
    }
}