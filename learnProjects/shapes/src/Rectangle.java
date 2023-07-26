package src;

/**
 * A class for rectangles, inheriting from Shape.
 * A rectangle has two parameters: length and width.
 */
public class Rectangle extends Shape {

    /**
     * Fields for the length of the rectangle
     */
    private double length;

    /**
     * Fields for the width of the rectangle
     */
    private double width;

    /**
     * A constructor with the length and width parameters.
     *
     * @param length the length of the rectangle
     * @param width  the width of the rectangle
     */
    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    /**
     * A getter for the length.
     */
    public double getLength() {
        return length;
    }

    /**
     * A setter for the length.
     */
    public void setLength(double length) {
        this.length = length;
    }

    /**
     * A getter for the width.
     */
    public double getWidth() {
        return width;
    }

    /**
     * A setter for the width.
     */
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * An override method for calculating the area of the rectangle.
     *
     * @return the area of the rectangle
     */
    @Override
    double getArea() {
        return length * width;
    }

    /**
     * An override method for calculating the perimeter of the rectangle.
     *
     * @return the perimeter of the rectangle
     */
    @Override
    double getPerimeter() {
        return (length + width) * 2;
    }
}
