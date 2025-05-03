public abstract class ReportService {
    protected LogService logService;
    public ReportService(LogService logService) {
        this.logService = logService;
    }
    public abstract void generateUserReport(List<User> users);
}