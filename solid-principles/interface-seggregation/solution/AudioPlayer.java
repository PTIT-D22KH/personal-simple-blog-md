public class AudioPlayer implements AudioPlayback, StorageCapable, ConnectivityCapable {
    @Override
    public void playAudio() {
        System.out.println("Playing audio...");
    }
    
    @Override
    public void storePlaylist() {
        System.out.println("Storing audio playlist...");
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