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

  public setLength(double length){
    this.length = length;
    updateCalculations();
  }
  
  public setWidth(double width){
    this.width = width;
    updateCalculations();
  }

  public summarizeCalculations

  private updateCalculations(){
    area = length * width;
    perimeter = 2 * length + 2 * width;
  }


}
