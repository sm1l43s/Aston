package src;

/**
 * A class for circles, inheriting from Shape.
 * A circle has one parameter: radius.
 */
public class Circle extends Shape {

    /**
     * A field for the radius of the circle
     */
    private double radius;

    /**
     * A constant for the number Pi
     */
    private static final double PI = 3.14159;

    /**
     * A constructor with the radius parameter.
     *
     * @param radius the radius of the circle
     */
    public Circle(double radius) {
        this.radius = radius;
    }

    /**
     * A getter for the radius.
     *
     * @return the radius of the circle
     */
    public double getRadius() {
        return radius;
    }

    /**
     * A setter for the radius.
     *
     * @param radius the new radius of the circle
     */
    public void setRadius(double radius) {
        this.radius = radius;
    }

    /**
     * An override method for calculating the area of the circle.
     *
     * @return the area of the circle
     */
    @Override
    double getArea() {
        return PI * radius * radius;
    }

    /**
     * An override method for calculating the perimeter of the circle.
     *
     * @return the perimeter of the circle
     */
    @Override
    double getPerimeter() {
        return 2 * PI * radius;
    }
}
