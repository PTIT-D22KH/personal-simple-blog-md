public class MediaPlayerClient {
    public void playMedia(MediaPlayer player) {
        // Try to play audio and video
        try {
            player.playAudio();
            player.playVideo();
        } catch (UnsupportedOperationException e) {
            System.out.println("Notice: " + e.getMessage());
        }
    }
    
    public void loadFromSDCard(MediaPlayer player) {
        player.readSDCard();
    }
    
    public void streamToBluetooth(MediaPlayer player) {
        player.connectBluetooth();
    }
}