public class XMLReportGenerator implements ReportGenerator {
    private LogService logService;
    
    public XMLReportGenerator(LogService logService) {
        this.logService = logService;
    }
    
    @Override
    public boolean generateReport(List<User> users, String fileName) {
        try (FileWriter writer = new FileWriter("users_report.xml")) {
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            writer.write("<users>\n");
            
            for (User user : users) {
                writer.write("  <user>\n");
                writer.write("    <id>" + user.getId() + "</id>\n");
                writer.write("    <fullName>" + user.getName() + "</fullName>\n");
                writer.write("    <email>" + user.getEmail() + "</email>\n");
                writer.write("  </user>\n");
            }
            
            writer.write("</users>");
            System.out.println("XML report generated successfully");
            return true;
        } catch (IOException e) {
            System.out.println("Error generating XML report: " + e.getMessage());
            logService.logError("Error generating XML report: " + e.getMessage());
            return false;
        }
    }
}