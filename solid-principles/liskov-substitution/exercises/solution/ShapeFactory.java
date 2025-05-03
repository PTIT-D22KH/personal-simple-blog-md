public class ShapeFactory {
    
    public static Shape createRectangle(int width, int height) {
        return new Rectangle(width, height);
    }
    
    public static Shape createSquare(int side) {
        return new Square(side);
    }
    
    public static void setRectangleWidth(Shape shape, int width) {
        if (shape instanceof Rectangle) {
            ((Rectangle) shape).setWidth(width);
        } else {
            throw new IllegalArgumentException("Not a rectangle");
        }
    }
    
    public static void setRectangleHeight(Shape shape, int height) {
        if (shape instanceof Rectangle) {
            ((Rectangle) shape).setHeight(height);
        } else {
            throw new IllegalArgumentException("Not a rectangle");
        }
    }
    
    public static int getRectangleWidth(Shape shape) {
        if (shape instanceof Rectangle) {
            return ((Rectangle) shape).getWidth();
        } else {
            throw new IllegalArgumentException("Not a rectangle");
        }
    }
    
    public static int getRectangleHeight(Shape shape) {
        if (shape instanceof Rectangle) {
            return ((Rectangle) shape).getHeight();
        } else {
            throw new IllegalArgumentException("Not a rectangle");
        }
    }
    
    public static void setSquareSide(Shape shape, int side) {
        if (shape instanceof Square) {
            ((Square) shape).setSide(side);
        } else {
            throw new IllegalArgumentException("Not a square");
        }
    }
    
    public static int getSquareSide(Shape shape) {
        if (shape instanceof Square) {
            return ((Square) shape).getSide();
        } else {
            throw new IllegalArgumentException("Not a square");
        }
    }
}