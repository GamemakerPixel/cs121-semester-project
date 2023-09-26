package week6.classactivity;



public class ClassActivityTest{
  public static void main(String[] args) {
    System.out.println("Rectangle Test: ");

    Rectangle rectangle = new Rectangle(4.3, 7.4);

    rectangle.summarizeCalculations();
    
    rectangle.setLength(5.2);
    rectangle.setWidth(8.9);
    rectangle.summarizeCalculations();

    System.out.println("Circle Test: ");

    Circle circle = new Circle(4.3);

    circle.summarizeCalculations();
    
    circle.setRadius(5.2);
    circle.summarizeCalculations();
  }
}
