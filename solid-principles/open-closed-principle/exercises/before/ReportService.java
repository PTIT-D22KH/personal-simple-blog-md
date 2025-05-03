public class ReportService {
    private LogService logService;
    public ReportService(LogService logService) {
        this.logService = logService;
    }
    public void generateUserReport(List<User> users, String format) {
        if ("CSV".equalsIgnoreCase(format)) {
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
        } else if ("JSON".equalsIgnoreCase(format)) {
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
            } catch (IOException e) {
                System.out.println("Error generating JSON report: " + e.getMessage());
                logService.logError("Error generating JSON report: " + e.getMessage());
            }
        }
    }
}