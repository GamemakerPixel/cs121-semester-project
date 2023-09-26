package week6.classactivity;



public class Rectangle{
  private double length;
  private double width;
  private double area;
  private double perimeter;

  public Rectangle(double length, double width){
    this.length = length;
    this.width = width;
    updateCalculations();
  }

  public void setLength(double length){
    this.length = length;
    updateCalculations();
  }
  
  public void setWidth(double width){
    this.width = width;
    updateCalculations();
  }

  public void summarizeCalculations(){
    System.out.printf(
        "Length: %.2f\n" +
        "Width: %.2f\n" +
        "Perimeter: %.2f\n" +
        "Area: %.2f\n",
        length, width, perimeter, area
    );
  }

  private void updateCalculations(){
    area = length * width;
    perimeter = 2 * length + 2 * width;
  }


}
