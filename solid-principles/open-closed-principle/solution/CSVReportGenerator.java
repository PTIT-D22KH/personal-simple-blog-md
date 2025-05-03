public class CSVReportGenerator implements ReportGenerator {
    private LogService logService;
    
    public CSVReportGenerator(LogService logService) {
        this.logService = logService;
    }
    
    @Override
    public boolean generateReport(List<User> users, String fileName) {
        try (FileWriter writer = new FileWriter("users_report.csv")) {
            writer.write("ID,Name,Email\n");
            for (User user : users) {
                writer.write(user.getId() + "," + user.getName() + "," + user.getEmail() + "\n");
            }
            System.out.println("CSV report generated successfully");
            return true;
        } catch (IOException e) {
            System.out.println("Error generating CSV report: " + e.getMessage());
            logService.logError("Error generating CSV report: " + e.getMessage());
            return false;
        }
    }
}