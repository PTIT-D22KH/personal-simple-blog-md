public class SMSSender implements MessageSender {
    @Override
    public void sendMessage(String to, String message) {
        // In a real application, this would use an SMS API to send a text message
        System.out.println("Sending SMS to: " + to);
        System.out.println("Message: " + message);
    }
}