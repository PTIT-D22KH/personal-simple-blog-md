public class ISPTestHelper {
    // Helper methods to check capabilities based on interfaces
    public static boolean isAudioCapable(Object player) {
        return player instanceof AudioPlayback;
    }
    
    public static boolean isVideoCapable(Object player) {
        return player instanceof VideoPlayback;
    }
    
    public static boolean isInternetCapable(Object player) {
        return player instanceof InternetCapable;
    }
}