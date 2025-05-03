public class EmailSender {
    public void sendEmail(String to, String subject, String body) {
        // In a real application, this would use an email API to send an email
        System.out.println("Sending email to: " + to);
        System.out.println("Subject: " + subject);
        System.out.println("Body: " + body);
    }
}