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