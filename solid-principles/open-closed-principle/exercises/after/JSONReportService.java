public class JSONReportService extends ReportService {

    public JSONReportService(LogService logService) {
        this.logService = logService;
    }

    public void generateUserReport(List<User> users) {
        try (FileWriter writer = new FileWriter("users_report.json")) {
            writer.write("[\n");
            for (int i = 0; i < users.size(); i++) {
                User user = users.get(i);
                writer.write("  {\n");
                writer.write("    \"id\": \"" + user.getId() + "\",\n");
                writer.write("    \"name\": \"" + user.getName() + "\",\n");
                writer.write("    \"email\": \"" + user.getEmail() + "\"\n");
                writer.write("  }" + (i < users.size() - 1 ? "," : "") + "\n");
            }
            writer.write("]\n");
            System.out.println("JSON report generated successfully");
        } catch (IOException e) {
            System.out.println("Error generating JSON report: " + e.getMessage());
            logService.logError("Error generating JSON report: " + e.getMessage());
        }
    }
}