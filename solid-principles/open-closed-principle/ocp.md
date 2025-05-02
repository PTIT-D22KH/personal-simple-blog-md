# Open Closed Principle (OCP)

## Statement
**Software entities (classes, modules, functions, etc.) should be open for extension but closed for modification.**
In other words, you should be able to add new functionality to a class without changing its existing code. This is important because it helps to reduce the risk of introducing bugs when modifying existing code.
## Examples of violate OCP

```java
class Shape {
    public double area() {
        // Calculate area
    }
}
class Circle extends Shape {
    private double radius;

    public double area() {
        return Math.PI * radius * radius;
    }
}
class Rectangle extends Shape {
    private double width;
    private double height;

    public double area() {
        return width * height;
    }
}
```
In this example, the `Shape` class is not open for extension because if we want to add a new shape, we have to modify the existing code. This violates the Open Closed Principle.
## Fixing

```java
interface Shape {
    double area();
}
class Circle implements Shape {
    private double radius;

    public double area() {
        return Math.PI * radius * radius;
    }
}
class Rectangle implements Shape {
    private double width;
    private double height;

    public double area() {
        return width * height;
    }
}
```
In this example, we have created an interface `Shape` that defines the `area` method. Now, if we want to add a new shape, we can simply create a new class that implements the `Shape` interface without modifying the existing code. This adheres to the Open Closed Principle.
