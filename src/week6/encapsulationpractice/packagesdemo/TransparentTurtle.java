package week6.encapsulationpractice.packagesdemo;



public class TransparentTurtle extends Turtle{
  public TransparentTurtle(String name, int scutes, String color, double age){
    super(name, scutes, color, age);
  }

  public String getColorFromSubclass(){
    return getColor();
  }

  public double getAgeFromSubclass(){
    return getAge();
  }

  /*
  public void setColorFromSubclass(String color){
    setColor(color);
  }*/
}
