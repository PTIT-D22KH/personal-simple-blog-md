import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportService {
    private LogService logService;
    private Map<String, ReportGenerator> reportGenerators;
    
    public ReportService(LogService logService) {
        this.logService = logService;
        this.reportGenerators = new HashMap<>();
        
        // Register default report generators
        registerReportGenerator("CSV", new CSVReportGenerator(logService));
        registerReportGenerator("JSON", new JSONReportGenerator(logService));
        registerReportGenerator("XML", new XMLReportGenerator(logService));
    }
    
    public void registerReportGenerator(String format, ReportGenerator generator) {
        reportGenerators.put(format.toUpperCase(), generator);
    }
    
    public boolean generateUserReport(List<User> users, String format) {
        if (format == null || !reportGenerators.containsKey(format.toUpperCase())) {
            System.out.println("Unsupported report format: " + format);
            return false;
        }
        
        ReportGenerator generator = reportGenerators.get(format.toUpperCase());
        return generator.generateReport(users, "users_report." + format.toLowerCase());
    }
}