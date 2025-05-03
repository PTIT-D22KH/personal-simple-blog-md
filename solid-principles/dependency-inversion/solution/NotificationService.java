import java.util.HashMap;
import java.util.Map;

public class NotificationService {
    private Map<String, NotificationChannel> channels = new HashMap<>();
    
    public void registerChannel(String channelName, NotificationChannel channel) {
        channels.put(channelName, channel);
    }
    
    public void notifyUser(String userId, String message, String channelName) {
        NotificationChannel channel = channels.get(channelName);
        if (channel != null) {
            channel.notify(userId, message);
        } else {
            System.out.println("Channel not found: " + channelName);
        }
    }
}