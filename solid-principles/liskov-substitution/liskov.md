# Liskov Substitution Principle (LSP)
## Principle
The Liskov Substitution Principle (LSP) states that objects of a superclass should be replaceable with objects of a subclass without affecting the correctness of the program. In other words, if class S is a subclass of class T, then we should be able to replace T with S without altering any of the desirable properties of the program.

In other words, subclasses should extend the behavior of their parent classes without changing the expected behavior. This means that a subclass should be able to stand in for its parent class without causing any issues.

## Examples of violations
### Example 1: Rectangle and Square
```java
// Base class
class Rectangle {
    private int width;
    private int height;

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getArea() {
        return width * height;
    }
}
// Subclass
class Square extends Rectangle {
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
In this example, the `Square` class violates the Liskov Substitution Principle because it does not behave like a rectangle. If we replace a `Rectangle` object with a `Square` object, the behavior of the program will change unexpectedly. For example, if we set the width and height of a rectangle to different values, we expect the area to be calculated as width * height. However, if we do the same with a square, it will always return the area as width * width.
### Example 2: Bird
```java
// Base class
class Bird {
    public void fly() {
        System.out.println("I can fly");
    }
}
// Subclass
class Sparrow extends Bird {
    @Override
    public void fly() {
        System.out.println("I am a sparrow and I can fly");
    }
}
// Subclass
class Ostrich extends Bird {
    @Override
    public void fly() {
        throw new UnsupportedOperationException("I cannot fly");
    }
}
```
In this example, the `Bird` class has a method `fly()`, which is overridden in the `Sparrow` subclass. However, the `Ostrich` subclass violates the Liskov Substitution Principle because it throws an exception when trying to call the `fly()` method, which is not expected behavior for a bird. This means that if we replace a `Bird` object with an `Ostrich` object, it will not behave as expected.
## Fixing
### Example 1: Rectangle and Square
```java
// Base class
abstract class Shape {
    public abstract int getArea();
}
// Subclass
class Rectangle extends Shape {
    private int width;
    private int height;

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public int getArea() {
        return width * height;
    }
}
// Subclass
class Square extends Shape {
    private int side;

    public void setSide(int side) {
        this.side = side;
    }

    @Override
    public int getArea() {
        return side * side;
    }
}
```
In this example, we have created an abstract class `Shape` that defines the method `getArea()`. The `Rectangle` and `Square` classes now extend the `Shape` class and implement the `getArea()` method. This way, both classes can be used interchangeably without violating the Liskov Substitution Principle.
### Example 2: Bird
```java
// Base class
abstract class Bird {
    public abstract void makeSound();
}
// Subclass
class Sparrow extends Bird {
    @Override
    public void makeSound() {
        System.out.println("Chirp");
    }
}
// Subclass
class Ostrich extends Bird {
    @Override
    public void makeSound() {
        System.out.println("Boom");
    }
}
```
In this example, we have created an abstract class `Bird` that defines the method `makeSound()`. The `Sparrow` and `Ostrich` classes now extend the `Bird` class and implement the `makeSound()` method. This way, both classes can be used interchangeably without violating the Liskov Substitution Principle.
