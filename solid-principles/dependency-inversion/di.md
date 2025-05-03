# Dependency inversion

## Principle
The Dependency Inversion Principle (DIP) states that high-level modules should not depend on low-level modules. Both should depend on abstractions. Abstractions should not depend on details. Details should depend on abstractions.
In a nutshell, the principle suggests that you should depend on interfaces or abstract classes rather than concrete implementations. This allows for more flexible and maintainable code.

**Only depend on things that don't change often**
## Examples
```java
interface Database {
    void connect();
}
class MySQLDatabase implements Database {
    public void connect() {
        // MySQL connection logic
    }
}
class PostgreSQLDatabase implements Database {
    public void connect() {
        // PostgreSQL connection logic
    }
}
class DatabaseManager {
    private Database database;

    public DatabaseManager(Database database) {
        this.database = database;
    }

    public void connect() {
        database.connect();
    }
}
```
In this example, `DatabaseManager` depends on the `Database` interface rather than a specific database implementation. This allows you to easily switch between different database implementations without changing the `DatabaseManager` class.