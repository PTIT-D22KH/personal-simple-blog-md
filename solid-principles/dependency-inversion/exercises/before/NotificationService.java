public class NotificationService {
    private EmailSender emailSender;
    private SMSSender smsSender;
    
    public NotificationService() {
        this.emailSender = new EmailSender();
        this.smsSender = new SMSSender();
    }
    
    public void notifyUser(String userId, String message, String channel) {
        if ("email".equals(channel)) {
            // Get user email
            String email = getUserEmail(userId);
            emailSender.sendEmail(email, "Notification", message);
        } else if ("sms".equals(channel)) {
            // Get user phone number
            String phoneNumber = getUserPhoneNumber(userId);
            smsSender.sendSMS(phoneNumber, message);
        }
    }
    
    private String getUserEmail(String userId) {
        // In a real application, this would fetch the email from a database
        return userId + "@example.com";
    }
    
    private String getUserPhoneNumber(String userId) {
        // In a real application, this would fetch the phone number from a database
        return "+1234567890";
    }
}