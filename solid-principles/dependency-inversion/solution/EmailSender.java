public class EmailSender implements MessageSender {
    @Override
    public void sendMessage(String to, String message) {
        // In a real application, this would use an email API to send an email
        System.out.println("Sending email to: " + to);
        System.out.println("Subject: Notification");
        System.out.println("Body: " + message);
    }
}