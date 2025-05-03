public class VideoPlayer implements AudioPlayback, VideoPlayback, StorageCapable, ConnectivityCapable {
    @Override
    public void playAudio() {
        System.out.println("Playing audio track of video...");
    }
    
    @Override
    public void playVideo() {
        System.out.println("Playing video...");
    }
    
    @Override
    public void displaySubtitles() {
        System.out.println("Displaying video subtitles...");
    }
    
    @Override
    public void storePlaylist() {
        System.out.println("Storing video playlist...");
    }
    
    @Override
    public void readSDCard() {
        System.out.println("Reading SD card for video files...");
    }
    
    @Override
    public void connectBluetooth() {
        System.out.println("Connecting to Bluetooth speakers...");
    }
}