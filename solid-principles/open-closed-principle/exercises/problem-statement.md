Collecting workspace information# Open-Closed Principle Exercise

## Problem Statement

### Background
The Open-Closed Principle (OCP) states that software entities (classes, modules, functions, etc.) should be open for extension but closed for modification. In other words, you should be able to add new functionality without changing existing code.

### Your Task
You've already improved the `UserManager` class by applying the Single Responsibility Principle. Now, let's focus on the `ReportService` class from your SRP solution. Currently, the `ReportService` violates the Open-Closed Principle because adding a new report format (like XML or HTML) would require modifying the existing `generateUserReport` method.

Your task is to refactor the `ReportService` to adhere to the OCP by:

1. Creating a design that allows adding new report formats without modifying existing code
2. Implementing the current CSV and JSON report formats using this new design
3. Adding a new XML report format as an example of extension without modification
4. Ensuring all tests pass

## Code that Violates OCP

Here's the current `ReportService` that violates OCP:

```java
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
```

## Testing Your Solution

Create the following test class to verify your OCP solution:

```java
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class OCPTest {
    
    private LogService logService;
    private ReportService reportService;
    private List<User> testUsers;
    
    @BeforeEach
    public void setUp() {
        logService = new LogService();
        reportService = new ReportService(logService);
        
        // Create test users
        User user1 = new User();
        user1.setId("user1");
        user1.setName("User One");
        user1.setEmail("user1@example.com");
        
        User user2 = new User();
        user2.setId("user2");
        user2.setName("User Two");
        user2.setEmail("user2@example.com");
        
        testUsers = Arrays.asList(user1, user2);
    }
    
    @Test
    public void testCSVReportGeneration() throws Exception {
        // Generate CSV report
        boolean result = reportService.generateUserReport(testUsers, "CSV");
        
        // Verify report was generated
        assertTrue(result, "CSV report should be generated successfully");
        assertTrue(new File("users_report.csv").exists(), "CSV file should exist");
        
        // Verify report content
        String content = new String(Files.readAllBytes(Paths.get("users_report.csv")));
        assertTrue(content.contains("ID,Name,Email"), "CSV should have header");
        assertTrue(content.contains("user1,User One,user1@example.com"), "CSV should contain user1 data");
        assertTrue(content.contains("user2,User Two,user2@example.com"), "CSV should contain user2 data");
    }
    
    @Test
    public void testJSONReportGeneration() throws Exception {
        // Generate JSON report
        boolean result = reportService.generateUserReport(testUsers, "JSON");
        
        // Verify report was generated
        assertTrue(result, "JSON report should be generated successfully");
        assertTrue(new File("users_report.json").exists(), "JSON file should exist");
        
        // Verify report content
        String content = new String(Files.readAllBytes(Paths.get("users_report.json")));
        assertTrue(content.contains("\"id\": \"user1\""), "JSON should contain user1 id");
        assertTrue(content.contains("\"name\": \"User One\""), "JSON should contain user1 name");
        assertTrue(content.contains("\"email\": \"user1@example.com\""), "JSON should contain user1 email");
        assertTrue(content.contains("\"id\": \"user2\""), "JSON should contain user2 id");
    }
    
    @Test
    public void testXMLReportGeneration() throws Exception {
        // Generate XML report
        boolean result = reportService.generateUserReport(testUsers, "XML");
        
        // Verify report was generated
        assertTrue(result, "XML report should be generated successfully");
        assertTrue(new File("users_report.xml").exists(), "XML file should exist");
        
        // Verify report content
        String content = new String(Files.readAllBytes(Paths.get("users_report.xml")));
        assertTrue(content.contains("<users>"), "XML should have root element");
        assertTrue(content.contains("<user>"), "XML should have user elements");
        assertTrue(content.contains("<id>user1</id>"), "XML should contain user1 id");
        assertTrue(content.contains("<name>User One</name>"), "XML should contain user1 name");
        assertTrue(content.contains("<email>user1@example.com</email>"), "XML should contain user1 email");
    }
    
    @Test
    public void testInvalidFormatHandling() {
        // Try to generate report with invalid format
        boolean result = reportService.generateUserReport(testUsers, "INVALID");
        
        // Verify behavior
        assertFalse(result, "Invalid format should return false");
    }
    
    /**
     * This test verifies that your solution follows the Open-Closed Principle
     * by checking that adding a new format doesn't require modifying existing code.
     */
    @Test
    public void testAddingNewFormat() throws Exception {
        // Create a new HTML report generator
        HTMLReportGenerator htmlGenerator = new HTMLReportGenerator(logService);
        
        // Register the new generator with the report service
        // Your solution should provide a way to register new formats
        reportService.registerReportGenerator("HTML", htmlGenerator);
        
        // Generate HTML report
        boolean result = reportService.generateUserReport(testUsers, "HTML");
        
        // Verify report was generated
        assertTrue(result, "HTML report should be generated successfully");
        assertTrue(new File("users_report.html").exists(), "HTML file should exist");
        
        // Verify report content
        String content = new String(Files.readAllBytes(Paths.get("users_report.html")));
        assertTrue(content.contains("<html>"), "HTML should have html tag");
        assertTrue(content.contains("<table>"), "HTML should have table tag");
        assertTrue(content.contains("<tr><th>ID</th><th>Name</th><th>Email</th></tr>"), "HTML should have table headers");
        assertTrue(content.contains("<tr><td>user1</td><td>User One</td><td>user1@example.com</td></tr>"), 
                "HTML should contain user1 data");
    }
}

/**
 * Example implementation of an HTML report generator
 * You should implement this class to test your OCP solution
 */
class HTMLReportGenerator implements ReportGenerator {
    private LogService logService;
    
    public HTMLReportGenerator(LogService logService) {
        this.logService = logService;
    }
    
    @Override
    public boolean generateReport(List<User> users, String fileName) {
        try (FileWriter writer = new FileWriter("users_report.html")) {
            writer.write("<html>\n<head><title>User Report</title></head>\n<body>\n");
            writer.write("<h1>User Report</h1>\n");
            writer.write("<table border=\"1\">\n");
            writer.write("<tr><th>ID</th><th>Name</th><th>Email</th></tr>\n");
            
            for (User user : users) {
                writer.write("<tr><td>" + user.getId() + "</td><td>" + 
                             user.getName() + "</td><td>" + 
                             user.getEmail() + "</td></tr>\n");
            }
            
            writer.write("</table>\n</body>\n</html>");
            System.out.println("HTML report generated successfully");
            return true;
        } catch (IOException e) {
            System.out.println("Error generating HTML report: " + e.getMessage());
            logService.logError("Error generating HTML report: " + e.getMessage());
            return false;
        }
    }
}
```

## Hints

1. Define an interface for report generation (e.g., `ReportGenerator`)
2. Create concrete implementations for each report format (CSV, JSON, XML)
3. Modify the `ReportService` to use these implementations
4. Provide a way to register new report formats without modifying the existing code
5. Make sure your tests pass for all report formats

Good luck! This exercise will help you understand how to apply the Open-Closed Principle to make your code more extensible.

Similar code found with 1 license type