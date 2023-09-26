package week6.encapsulationpractice.packagesdemo;



public class Turtle{
  public String name;
  public int scutes;
  private String color;
  private double age;

  public Turtle(String name, int scutes, String color, double age){
    this.name = name;
    this.scutes = scutes;
    this.color = color;
    this.age = age;
  }

  public void printTurtle(){
    System.out.println(
        "      ##\n" +
        " ###  ##\n" +
        "######  \n" +
        "#####   \n" +
        "#   #   "
        );
  }

  //package-private, acts like protected when in the same package
  double getAge(){
    return age;
  }

  protected String getColor(){
    return color;
  }

  private void setColor(String color){
    this.color = color;
  }
  
}
