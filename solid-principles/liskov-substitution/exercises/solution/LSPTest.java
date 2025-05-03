import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LSPTest {
    
    @Test
    public void testRectangleArea() {
        // Create a rectangle with width 5 and height 10
        Shape rectangle = ShapeFactory.createRectangle(5, 10);
        
        // Test area calculation
        assertEquals(50, rectangle.getArea());
        
        // Test that changing dimensions works correctly
        ShapeFactory.setRectangleWidth(rectangle, 7);
        ShapeFactory.setRectangleHeight(rectangle, 12);
        
        assertEquals(7, ShapeFactory.getRectangleWidth(rectangle));
        assertEquals(12, ShapeFactory.getRectangleHeight(rectangle));
        assertEquals(84, rectangle.getArea());
    }
    
    @Test
    public void testSquareArea() {
        // Create a square with side 5
        Shape square = ShapeFactory.createSquare(5);
        
        // Test area calculation
        assertEquals(25, square.getArea());
        
        // Test that changing dimensions works correctly
        ShapeFactory.setSquareSide(square, 7);
        
        assertEquals(7, ShapeFactory.getSquareSide(square));
        assertEquals(49, square.getArea());
    }
    
    @Test
    public void testLiskovSubstitutionPrinciple() {
        // This test verifies that the solution follows LSP
        
        // Create a list of shapes (mix of rectangles and squares)
        Shape[] shapes = {
            ShapeFactory.createRectangle(4, 5),
            ShapeFactory.createSquare(5),
            ShapeFactory.createRectangle(3, 7),
            ShapeFactory.createSquare(8)
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
        Shape rectangle = ShapeFactory.createRectangle(5, 10);
        
        // Remember the original height
        int originalHeight = ShapeFactory.getRectangleHeight(rectangle);
        
        // Change only the width
        ShapeFactory.setRectangleWidth(rectangle, 7);
        
        // The height should remain unchanged
        assertEquals(originalHeight, ShapeFactory.getRectangleHeight(rectangle));
        assertEquals(7 * originalHeight, rectangle.getArea());
    }
}