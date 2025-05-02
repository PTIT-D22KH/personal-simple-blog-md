# Single Responsibility Principle

## Statement
**A class should have only one reason to change**

In other words, a class should have only one job or purpose in the software
## Examples of violate SRP

```java

class Employee {
    private long id;
    private String name;
    private String department;
    private boolean working;

    public boolean saveToDb();
    public void printReportXML();
    public void printReportCSV();
    public boolean terminateEmployee();
}
```
You can see that the class `Employee` has multiple responsibilities:
- Saving to the database
- Printing reports in XML and CSV formats
- Terminating an employee
, which vilolates the Single Responsibility Principle.
## Fixing

```java

class Employee {
    private long id;
    private String name;
    private String department;
    private boolean working;
}

class DatabaseConnectionManager {
    private static DatabaseConnectionManager instance;
    private Connection connection;
    private DatabaseConnectionManager() {
        // Initialize the connection
    }
    public static DatabaseConnectionManager getInstance() {
        if (instance == null) {
            instance = new DatabaseConnectionManager();
        }
        return instance;
    }
    public Connection getConnection() {
        return connection;
    }
}
class EmployeeRepository {
    public boolean saveToDb(Employee employee) {
        // Save employee to the database
    }
}
class EmployeeReport {
    public void printReportXML(Employee employee) {
        // Print employee report in XML format
    }
    public void printReportCSV(Employee employee) {
        // Print employee report in CSV format
    }
}
class EmployeeTermination {
    public boolean terminateEmployee(Employee employee) {
        // Terminate the employee
    }
}

```