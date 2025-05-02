# Interface Segregation Principle (ISP)
## Definition
The Interface Segregation Principle (ISP) states that no client should be forced to depend on methods it does not use. This means that interfaces should be small and specific to the clients that use them, rather than large and general-purpose.

In other words, clients should not be forced to implement interfaces that they do not need. This principle helps to reduce the impact of changes in the system and makes it easier to understand and maintain the code.
## Example
```java
// Bad Example
interface Animal {
    void eat();
    void fly();
    void swim();
}
class Dog implements Animal {
    public void eat() {
        System.out.println("Dog is eating");
    }

    public void fly() {
        // Not applicable for Dog
    }

    public void swim() {
        // Not applicable for Dog
    }
}
class Bird implements Animal {
    public void eat() {
        System.out.println("Bird is eating");
    }

    public void fly() {
        System.out.println("Bird is flying");
    }

    public void swim() {
        // Not applicable for Bird
    }
}
// Good Example
interface Eater {
    void eat();
}
interface Flyer {
    void fly();
}
interface Swimmer {
    void swim();
}
class Dog implements Eater {
    public void eat() {
        System.out.println("Dog is eating");
    }
}
class Bird implements Eater, Flyer {
    public void eat() {
        System.out.println("Bird is eating");
    }

    public void fly() {
        System.out.println("Bird is flying");
    }
}
class Fish implements Eater, Swimmer {
    public void eat() {
        System.out.println("Fish is eating");
    }

    public void swim() {
        System.out.println("Fish is swimming");
    }
}
```

