public class SMSSender {
    public void sendSMS(String phoneNumber, String message) {
        // In a real application, this would use an SMS API to send a text message
        System.out.println("Sending SMS to: " + phoneNumber);
        System.out.println("Message: " + message);
    }
}