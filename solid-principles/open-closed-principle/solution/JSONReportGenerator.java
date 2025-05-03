public class JSONReportGenerator implements ReportGenerator {
    private LogService logService;
    
    public JSONReportGenerator(LogService logService) {
        this.logService = logService;
    }
    
    @Override
    public boolean generateReport(List<User> users, String fileName) {
        try (FileWriter writer = new FileWriter("users_report.json")) {
            StringBuilder json = new StringBuilder("[\n");
            for (int i = 0; i < users.size(); i++) {
                User user = users.get(i);
                json.append("  {\n");
                json.append("    \"id\": \"").append(user.getId()).append("\",\n");
                json.append("    \"name\": \"").append(user.getName()).append("\",\n");
                json.append("    \"email\": \"").append(user.getEmail()).append("\"\n");
                json.append("  }");
                if (i < users.size() - 1) {
                    json.append(",");
                }
                json.append("\n");
            }
            json.append("]\n");
            writer.write(json.toString());
            System.out.println("JSON report generated successfully");
            return true;
        } catch (IOException e) {
            System.out.println("Error generating JSON report: " + e.getMessage());
            logService.logError("Error generating JSON report: " + e.getMessage());
            return false;
        }
    }
}