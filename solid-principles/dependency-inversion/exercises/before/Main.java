public class Main {
    public static void main(String[] args) {
        NotificationService notificationService = new NotificationService();
        
        // Send an email notification
        notificationService.notifyUser("user123", "Your order has been shipped!", "email");
        
        System.out.println("------------------------------");
        
        // Send an SMS notification
        notificationService.notifyUser("user456", "Your package will arrive today!", "sms");
    }
}