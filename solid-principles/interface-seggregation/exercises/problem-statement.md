Collecting workspace information# Interface Segregation Principle Exercise

## Problem Statement

### Background
The Interface Segregation Principle (ISP) states that no client should be forced to depend on methods it does not use. This means that interfaces should be small, focused, and tailored to specific client needs rather than being large and general-purpose.

### Your Task
Below is a `MediaPlayer` interface with implementations that violate the Interface Segregation Principle. Your task is to refactor this code to adhere to the ISP by:

1. Identifying why the current implementation violates ISP
2. Breaking down the large interface into smaller, more specific interfaces
3. Making the client classes depend only on the interfaces they need
4. Ensuring all tests pass

## Code that Violates ISP

```java
// This interface violates ISP by including methods that not all clients need
public interface MediaPlayer {
    void playAudio();
    void playVideo();
    void displaySubtitles();
    void storePlaylist();
    void browseInternet();
    void readSDCard();
    void connectBluetooth();
}

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

// SmartMediaPlayer implements all functionalities
public class SmartMediaPlayer implements MediaPlayer {
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
```

This is used by client code like this:

```java
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
```

## Testing Your Solution

Create the following test class to verify your ISP solution:

```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ISPTest {
    
    @Test
    public void testAudioPlayer() {
        // Create an audio player
        AudioPlayer audioPlayer = createAudioPlayer();
        
        // Test audio functionality
        assertDoesNotThrow(() -> {
            audioPlayer.playAudio();
            audioPlayer.storePlaylist();
            audioPlayer.readSDCard();
            audioPlayer.connectBluetooth();
        }, "AudioPlayer should support these operations");
        
        // Verify the player doesn't implement video functionality
        assertFalse(isVideoCapable(audioPlayer), 
            "AudioPlayer should not implement video functionality");
        
        // Verify the player doesn't implement internet browsing
        assertFalse(isInternetCapable(audioPlayer), 
            "AudioPlayer should not implement internet browsing");
    }
    
    @Test
    public void testVideoPlayer() {
        // Create a video player
        VideoPlayer videoPlayer = createVideoPlayer();
        
        // Test video functionality
        assertDoesNotThrow(() -> {
            videoPlayer.playVideo();
            videoPlayer.displaySubtitles();
        }, "VideoPlayer should support video operations");
        
        // Test audio functionality
        assertDoesNotThrow(() -> {
            videoPlayer.playAudio();
            videoPlayer.storePlaylist();
            videoPlayer.readSDCard();
            videoPlayer.connectBluetooth();
        }, "VideoPlayer should support audio operations");
        
        // Verify the player doesn't implement internet browsing
        assertFalse(isInternetCapable(videoPlayer), 
            "VideoPlayer should not implement internet browsing");
    }
    
    @Test
    public void testSmartMediaPlayer() {
        // Create a smart media player
        SmartMediaPlayer smartPlayer = createSmartMediaPlayer();
        
        // Test all functionality
        assertDoesNotThrow(() -> {
            smartPlayer.playAudio();
            smartPlayer.playVideo();
            smartPlayer.displaySubtitles();
            smartPlayer.storePlaylist();
            smartPlayer.browseInternet();
            smartPlayer.readSDCard();
            smartPlayer.connectBluetooth();
        }, "SmartMediaPlayer should support all operations");
        
        // Verify the player implements all capabilities
        assertTrue(isAudioCapable(smartPlayer), 
            "SmartMediaPlayer should implement audio functionality");
        assertTrue(isVideoCapable(smartPlayer), 
            "SmartMediaPlayer should implement video functionality");
        assertTrue(isInternetCapable(smartPlayer), 
            "SmartMediaPlayer should implement internet browsing");
    }
    
    @Test
    public void testClientWithDifferentPlayers() {
        // Create media players
        AudioPlayer audioPlayer = createAudioPlayer();
        VideoPlayer videoPlayer = createVideoPlayer();
        SmartMediaPlayer smartPlayer = createSmartMediaPlayer();
        
        // Create client that works with any player
        MediaPlayerClient client = new MediaPlayerClient();
        
        // Test with audio player
        assertDoesNotThrow(() -> {
            client.loadFromSDCard(audioPlayer);
            client.streamToBluetooth(audioPlayer);
        }, "Client should work with AudioPlayer");
        
        // Test with video player
        assertDoesNotThrow(() -> {
            client.playMedia(videoPlayer);
            client.loadFromSDCard(videoPlayer);
            client.streamToBluetooth(videoPlayer);
        }, "Client should work with VideoPlayer");
        
        // Test with smart player
        assertDoesNotThrow(() -> {
            client.playMedia(smartPlayer);
            client.loadFromSDCard(smartPlayer);
            client.streamToBluetooth(smartPlayer);
        }, "Client should work with SmartMediaPlayer");
    }
    
    // Helper methods to create players - implement these based on your solution
    private AudioPlayer createAudioPlayer() {
        // Create and return an audio player
        return new AudioPlayer();
    }
    
    private VideoPlayer createVideoPlayer() {
        // Create and return a video player
        return new VideoPlayer();
    }
    
    private SmartMediaPlayer createSmartMediaPlayer() {
        // Create and return a smart media player
        return new SmartMediaPlayer();
    }
    
    // Helper methods to check capabilities - implement these based on your solution
    private boolean isAudioCapable(Object player) {
        // Check if the player implements audio functionality
        // Implement based on your solution
        return false; // Placeholder
    }
    
    private boolean isVideoCapable(Object player) {
        // Check if the player implements video functionality
        // Implement based on your solution
        return false; // Placeholder
    }
    
    private boolean isInternetCapable(Object player) {
        // Check if the player implements internet browsing
        // Implement based on your solution
        return false; // Placeholder
    }
}
```

## Explanation of the ISP Violation

The current implementation violates the Interface Segregation Principle because:

1. The `MediaPlayer` interface is a "fat" interface with too many methods
2. Clients (`AudioPlayer`, `VideoPlayer`) are forced to implement methods they don't need
3. This leads to throwing `UnsupportedOperationException` in several methods
4. Clients that use these players have no way to know which operations are supported without catching exceptions

## Hints

1. Break down the large `MediaPlayer` interface into smaller, more focused interfaces (e.g., `AudioPlayback`, `VideoPlayback`, `StorageCapable`, etc.)
2. Make each player class implement only the interfaces it needs
3. Consider using composition to combine capabilities when needed
4. Use interface inheritance where appropriate
5. Update the client code to work with the specific interfaces rather than the general `MediaPlayer`

Good luck! This exercise will help you understand how to apply the Interface Segregation Principle to create more maintainable and flexible interfaces.