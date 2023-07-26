package src;

/**
 * A class with the main method of the program.
 * The main method creates objects of different shapes and prints their information.
 */
public class Main {
    public static void main(String[] args) {

        Shape circle = new Circle(5);
        Shape rectangle = new Rectangle(10, 20);
        Shape triangle = new Triangle(3, 4, 5);

        // to print on the console
        System.out.println("Circle: area = " + circle.getArea() + ", perimeter = " + circle.getPerimeter());
        System.out.println("Rectangle: area = " + rectangle.getArea() + ", perimeter = " + rectangle.getPerimeter());
        System.out.println("Triangle: area = " + triangle.getArea() + ", perimeter = " + triangle.getPerimeter());
    }
}