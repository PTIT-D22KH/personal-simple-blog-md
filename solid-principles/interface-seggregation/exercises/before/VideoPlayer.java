// VideoPlayer needs video and audio functionality but not internet browsing
public class VideoPlayer implements MediaPlayer {
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
    public void browseInternet() {
        // Not supported by basic video player
        throw new UnsupportedOperationException("Internet browsing not supported");
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