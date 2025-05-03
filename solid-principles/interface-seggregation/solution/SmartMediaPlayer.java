public class SmartMediaPlayer implements AudioPlayback, VideoPlayback, StorageCapable, 
                                     ConnectivityCapable, InternetCapable {
    @Override
    public void playAudio() {
        System.out.println("Smart player playing audio...");
    }
    
    @Override
    public void playVideo() {
        System.out.println("Smart player playing video...");
    }
    
    @Override
    public void displaySubtitles() {
        System.out.println("Smart player displaying subtitles...");
    }
    
    @Override
    public void storePlaylist() {
        System.out.println("Smart player storing playlist...");
    }
    
    @Override
    public void browseInternet() {
        System.out.println("Smart player browsing internet...");
    }
    
    @Override
    public void readSDCard() {
        System.out.println("Smart player reading SD card...");
    }
    
    @Override
    public void connectBluetooth() {
        System.out.println("Smart player connecting to Bluetooth device...");
    }
}