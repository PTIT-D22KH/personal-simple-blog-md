public class Square extends Shape {
    private int side;
    public Square() {
        this.side = 0;
    }
    public Square(int side) {
        this.side = side;
    }
    @Override
    public int getArea() {
        return side * side;
    }
    public void setSide(int side) {
        this.side = side;
    }
    public int getSide() {
        return side;
    }
}