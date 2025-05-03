// AudioPlayer only needs audio functionality but is forced to implement all methods
public class AudioPlayer implements MediaPlayer {
    @Override
    public void playAudio() {
        System.out.println("Playing audio...");
    }
    
    @Override
    public void playVideo() {
        // Not supported by audio player
        throw new UnsupportedOperationException("Video playback not supported");
    }
    
    @Override
    public void displaySubtitles() {
        // Not supported by audio player
        throw new UnsupportedOperationException("Subtitles not supported");
    }
    
    @Override
    public void storePlaylist() {
        System.out.println("Storing audio playlist...");
    }
    
    @Override
    public void browseInternet() {
        // Not supported by audio player
        throw new UnsupportedOperationException("Internet browsing not supported");
    }
    
    @Override
    public void readSDCard() {
        System.out.println("Reading SD card for audio files...");
    }
    
    @Override
    public void connectBluetooth() {
        System.out.println("Connecting to Bluetooth audio device...");
    }
}