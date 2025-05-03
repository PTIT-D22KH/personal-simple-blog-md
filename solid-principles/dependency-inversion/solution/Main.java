public class Main {
    public static void main(String[] args) {
        // Create the notification service
        NotificationService notificationService = new NotificationService();
        
        // Set up email channel
        MessageSender emailSender = new EmailSender();
        ContactResolver emailResolver = new EmailContactResolver();
        NotificationChannel emailChannel = new NotificationChannel(emailSender, emailResolver);
        
        // Set up SMS channel
        MessageSender smsSender = new SMSSender();
        ContactResolver phoneResolver = new PhoneContactResolver();
        NotificationChannel smsChannel = new NotificationChannel(smsSender, phoneResolver);
        
        // Register the channels
        notificationService.registerChannel("email", emailChannel);
        notificationService.registerChannel("sms", smsChannel);
        
        // Send an email notification
        notificationService.notifyUser("user123", "Your order has been shipped!", "email");
        
        System.out.println("------------------------------");
        
        // Send an SMS notification
        notificationService.notifyUser("user456", "Your package will arrive today!", "sms");
    }
}