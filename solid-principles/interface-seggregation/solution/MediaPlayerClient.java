public class MediaPlayerClient {
    // For playing media (audio or video)
    public void playMedia(Object player) {
        // Play audio if the player supports it
        if (player instanceof AudioPlayback) {
            ((AudioPlayback) player).playAudio();
        }
        
        // Play video if the player supports it
        if (player instanceof VideoPlayback) {
            ((VideoPlayback) player).playVideo();
        }
    }
    
    // For loading from SD card
    public void loadFromSDCard(StorageCapable player) {
        player.readSDCard();
    }
    
    // For streaming to Bluetooth
    public void streamToBluetooth(ConnectivityCapable player) {
        player.connectBluetooth();
    }
}