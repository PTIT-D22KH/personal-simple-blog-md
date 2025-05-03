Collecting workspace information# Liskov Substitution Principle Exercise

## Problem Statement

### Background
The Liskov Substitution Principle (LSP) states that objects of a superclass should be replaceable with objects of its subclasses without affecting the correctness of the program. In other words, derived classes must be completely substitutable for their base classes.

### Your Task
Below is a `Shape` hierarchy with a `Rectangle` and `Square` class that violates the Liskov Substitution Principle. Your task is to refactor this code to adhere to the LSP by:

1. Identifying why the current implementation violates LSP
2. Restructuring the class hierarchy to fix the violations
3. Ensuring clients of these classes can use them interchangeably
4. Making all tests pass

## Code that Violates LSP

```java
// This hierarchy violates the Liskov Substitution Principle
public class Rectangle {
    protected int width;
    protected int height;
    
    public Rectangle() {
        this.width = 0;
        this.height = 0;
    }
    
    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }
    
    public void setWidth(int width) {
        this.width = width;
    }
    
    public void setHeight(int height) {
        this.height = height;
    }
    
    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }
    
    public int getArea() {
        return width * height;
    }
}

public class Square extends Rectangle {
    public Square() {
        super(0, 0);
    }
    
    public Square(int side) {
        super(side, side);
    }
    
    @Override
    public void setWidth(int width) {
        super.setWidth(width);
        super.setHeight(width);
    }
    
    @Override
    public void setHeight(int height) {
        super.setWidth(height);
        super.setHeight(height);
    }
}
```

This code is used by client code like this:

```java
public class AreaCalculator {
    public void increaseRectangleWidth(Rectangle rectangle) {
        // Save the original height
        int height = rectangle.getHeight();
        
        // Increase width by 2
        rectangle.setWidth(rectangle.getWidth() + 2);
        
        // Verify that only the width changed
        assert rectangle.getHeight() == height : 
            "Height should not change when width is modified!";
    }
}
```

## Testing Your Solution

Create the following test class to verify your LSP solution:

```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LSPTest {
    
    @Test
    public void testRectangleArea() {
        // Create a rectangle with width 5 and height 10
        Shape rectangle = createRectangle(5, 10);
        
        // Test area calculation
        assertEquals(50, rectangle.getArea());
        
        // Test that changing dimensions works correctly
        setRectangleWidth(rectangle, 7);
        setRectangleHeight(rectangle, 12);
        
        assertEquals(7, getRectangleWidth(rectangle));
        assertEquals(12, getRectangleHeight(rectangle));
        assertEquals(84, rectangle.getArea());
    }
    
    @Test
    public void testSquareArea() {
        // Create a square with side 5
        Shape square = createSquare(5);
        
        // Test area calculation
        assertEquals(25, square.getArea());
        
        // Test that changing dimensions works correctly
        setSquareSide(square, 7);
        
        assertEquals(7, getSquareSide(square));
        assertEquals(49, square.getArea());
    }
    
    @Test
    public void testLiskovSubstitutionPrinciple() {
        // This test verifies that your solution follows LSP
        
        // Create a list of shapes (mix of rectangles and squares)
        Shape[] shapes = {
            createRectangle(4, 5),
            createSquare(5),
            createRectangle(3, 7),
            createSquare(8)
        };
        
        // Calculate total area - this should work without knowing the specific types
        int totalArea = 0;
        for (Shape shape : shapes) {
            totalArea += shape.getArea();
        }
        
        // 4*5 + 5*5 + 3*7 + 8*8 = 20 + 25 + 21 + 64 = 130
        assertEquals(130, totalArea);
    }
    
    @Test
    public void testRectangleInvariant() {
        // Create a rectangle with width 5 and height 10
        Shape rectangle = createRectangle(5, 10);
        
        // Remember the original height
        int originalHeight = getRectangleHeight(rectangle);
        
        // Change only the width
        setRectangleWidth(rectangle, 7);
        
        // The height should remain unchanged
        assertEquals(originalHeight, getRectangleHeight(rectangle));
        assertEquals(7 * originalHeight, rectangle.getArea());
    }
    
    // Your implementation should provide methods to create and manipulate shapes
    // For example:
    
    // Helper methods to create shapes - implement these based on your solution
    private Shape createRectangle(int width, int height) {
        // Return a rectangle with the given width and height
        // Implement based on your solution
        return null; // Placeholder
    }
    
    private Shape createSquare(int side) {
        // Return a square with the given side length
        // Implement based on your solution
        return null; // Placeholder
    }
    
    // Helper methods to manipulate rectangle - implement these based on your solution
    private void setRectangleWidth(Shape shape, int width) {
        // Set the width of the rectangle
        // Implement based on your solution
    }
    
    private void setRectangleHeight(Shape shape, int height) {
        // Set the height of the rectangle
        // Implement based on your solution
    }
    
    private int getRectangleWidth(Shape shape) {
        // Get the width of the rectangle
        // Implement based on your solution
        return 0; // Placeholder
    }
    
    private int getRectangleHeight(Shape shape) {
        // Get the height of the rectangle
        // Implement based on your solution
        return 0; // Placeholder
    }
    
    // Helper methods for square - implement these based on your solution
    private void setSquareSide(Shape shape, int side) {
        // Set the side length of the square
        // Implement based on your solution
    }
    
    private int getSquareSide(Shape shape) {
        // Get the side length of the square
        // Implement based on your solution
        return 0; // Placeholder
    }
}
```

## Explanation of the LSP Violation

The current implementation violates the Liskov Substitution Principle because:

1. The `Square` class overrides the `setWidth` and `setHeight` methods to maintain the invariant that a square has equal sides.
2. This means a `Square` cannot be substituted for a `Rectangle` without breaking client code that expects changing the width to not affect the height.
3. The assertion in the `AreaCalculator.increaseRectangleWidth` method will fail when passed a `Square` object.

## Hints

1. Consider introducing a common base abstraction (interface or abstract class) for both shapes
2. Make sure that derived classes don't strengthen preconditions or weaken postconditions
3. Avoid inheritance relationships that force you to violate class invariants
4. Think about what properties and behaviors are truly common to all shapes
5. Consider using composition over inheritance where appropriate

Good luck! This exercise will help you understand how to apply the Liskov Substitution Principle to create more robust class hierarchies.

Similar code found with 1 license type