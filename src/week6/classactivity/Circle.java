package week6.classactivity;



public class Circle{
  private double radius;
  private double area;
  private double circumference;

  public Circle(double radius){
    this.radius = radius;
    updateCalculations();
  }

  public void setRadius(double radius){
    this.radius = radius;
    updateCalculations();
  }
  
  public void summarizeCalculations(){
    System.out.printf(
        "Radius: %.2f\n" +
        "Circumference: %.2f\n" +
        "Area: %.2f\n",
        radius, circumference, area
    );
  }

  private void updateCalculations(){
    area = 4 * Math.PI * radius * radius;
    circumference = 2 * radius * Math.PI;
  }


}
