package src;

/**
 * A class for triangles, inheriting from Shape.
 * A triangle has three parameters: a, b and c, representing its sides.
 */
public class Triangle extends Shape {

    /**
     * Fields for the sides of the triangle
     */
    private double a;
    private double b;
    private double c;

    /**
     * A constructor with the parameters a, b and c.
     *
     * @param a the first side of the triangle
     * @param b the second side of the triangle
     * @param c the third side of the triangle
     */
    public Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * A getter for a sides.
     */
    public double getA() {
        return a;
    }

    /**
     * A setter for a sides.
     */
    public void setA(double a) {
        this.a = a;
    }

    /**
     * A getter for b sides.
     */
    public double getB() {
        return b;
    }

    /**
     * A setter for b sides.
     */
    public void setB(double b) {
        this.b = b;
    }

    /**
     * A getters for c sides.
     */
    public double getC() {
        return c;
    }

    /**
     * A setters for c sides.
     */
    public void setC(double c) {
        this.c = c;
    }

    /**
     * An override method for calculating the area of the triangle using Heron's formula.
     *
     * @return the area of the triangle
     */
    @Override
    double getArea() {
        double p = (a + b + c) / 2;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }

    /**
     * An override method for calculating the perimeter of the triangle.
     *
     * @return the perimeter of the triangle
     */
    @Override
    double getPerimeter() {
        return a + b + c;
    }
}
