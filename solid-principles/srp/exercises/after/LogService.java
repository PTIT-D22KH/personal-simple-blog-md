public class LogService {
    public void logError(String errorMessage) {
        try (FileWriter writer = new FileWriter("error_log.txt", true)) {
            writer.write(new Date() + ": " + errorMessage + "\n");
        } catch (IOException e) {
            System.out.println("Error writing to log file: " + e.getMessage());
        }
    }
}