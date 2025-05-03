public class CSVReportService extends ReportService {
    public CSVReportService(LogService logService) {
        this.logService = logService;
    }
    public void generateUserReport(List<User> users) {
        try (FileWriter writer = new FileWriter("users_report.csv")) {
            writer.write("ID,Name,Email\n");
            for (User user : users) {
                writer.write(user.getId() + "," + user.getName() + "," + user.getEmail() + "\n");
            }
            System.out.println("CSV report generated successfully");
        } catch (IOException e) {
            System.out.println("Error generating CSV report: " + e.getMessage());
            logService.logError("Error generating CSV report: " + e.getMessage());
        }  
    }
    
}