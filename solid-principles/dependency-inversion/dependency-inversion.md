# Dependency Inversion Principle (DIP)

## Definition
The Dependency Inversion Principle (DIP) is one of the five SOLID principles of object-oriented design. It consists of two key parts:

1. **High-level modules should not depend on low-level modules. Both should depend on abstractions.**
2. **Abstractions should not depend on details. Details should depend on abstractions.**

In simpler terms, this principle suggests that the code that contains the business logic (high-level modules) should not depend directly on the code that implements specific operations (low-level modules). Instead, both should depend on abstractions (interfaces or abstract classes).

## Importance
- **Reduces coupling**: Makes the codebase more flexible and maintainable
- **Improves testability**: Makes it easier to write unit tests by enabling mock implementations
- **Facilitates changes**: Allows you to swap implementations without modifying high-level code

## Common Violations

### Directly instantiating dependencies
```java
public class NotificationService {
    private EmailSender emailSender = new EmailSender(); // Direct instantiation
    
    public void notify(String message) {
        emailSender.sendEmail("user@example.com", "Notification", message);
    }
}
```

### Making high-level modules depend on low-level modules
```java
public class UserService {
    private MySQLUserRepository repository; // Depends on a specific implementation
    
    public UserService() {
        this.repository = new MySQLUserRepository();
    }
}
```

## How to Apply DIP

1. **Identify dependencies**: Determine which classes are directly dependent on other concrete classes
2. **Define abstractions**: Create interfaces or abstract classes that capture the essential behavior
3. **Invert dependencies**: Make both high-level and low-level modules depend on the abstractions
4. **Use dependency injection**: Pass dependencies through constructors, setters, or method parameters

## The Notification System Example

### Before: Violating DIP

In the original code, the `NotificationService` directly creates instances of `EmailSender` and `SMSSender`:

```java
public class NotificationService {
    private EmailSender emailSender;
    private SMSSender smsSender;
    
    public NotificationService() {
        this.emailSender = new EmailSender();
        this.smsSender = new SMSSender();
    }
    
    // ... rest of the code
}
```

This creates tight coupling between the high-level `NotificationService` and the low-level sender implementations. If we want to:
- Test the `NotificationService` in isolation
- Add a new notification channel (like push notifications)
- Change how email or SMS is sent

We would need to modify the `NotificationService` class directly, violating the Open-Closed Principle.

### After: Following DIP

In the refactored solution:

1. We created abstractions:
   - `MessageSender` interface for different sending mechanisms
   - `ContactResolver` interface for resolving user contact information

2. Both high-level and low-level components depend on these abstractions:
   - `NotificationService` depends on `NotificationChannel`
   - `NotificationChannel` depends on `MessageSender` and `ContactResolver`
   - Concrete implementations like `EmailSender` implement these interfaces

3. Dependencies are injected:
   ```java
   // Dependencies are passed in, not created inside
   NotificationChannel emailChannel = new NotificationChannel(emailSender, emailResolver);
   ```

## Benefits of the Refactored Solution

1. **Extensibility**: We can add new notification channels (like push notifications) without changing existing code.

2. **Testability**: We can create mock implementations of `MessageSender` and `ContactResolver` for testing.

3. **Flexibility**: We can swap implementations at runtime (e.g., switch from one email service to another).

4. **Decoupling**: High-level business logic is separated from low-level implementation details.

## Best Practices

1. **Use constructor injection** for required dependencies
2. **Use setter injection** for optional dependencies
3. **Avoid the Service Locator pattern** (it hides dependencies)
4. **Name interfaces based on what they do**, not what they are
5. **Keep interfaces focused and cohesive**

## Conclusion

The Dependency Inversion Principle is about decoupling the high-level policy from the low-level details. By depending on abstractions, we make our code more flexible, testable, and maintainable.

Remember: "Program to an interface, not an implementation."