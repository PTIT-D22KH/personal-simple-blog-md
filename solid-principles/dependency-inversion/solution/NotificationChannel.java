public class NotificationChannel {
    private MessageSender messageSender;
    private ContactResolver contactResolver;

    public NotificationChannel(MessageSender messageSender, ContactResolver contactResolver) {
        this.messageSender = messageSender;
        this.contactResolver = contactResolver;
    }
    
    public void notify(String userId, String message) {
        String contactInfo = contactResolver.getUserContact(userId);
        messageSender.sendMessage(contactInfo, message);
    }
}