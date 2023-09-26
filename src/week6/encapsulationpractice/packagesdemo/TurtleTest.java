package week6.encapsulationpractice.packagesdemo;



public class TurtleTest{
  public static void main(String[] args) {
    Turtle turtle = new Turtle("Bert", 20, "Green", 6.78);
    TransparentTurtle transparentTurtle = new TransparentTurtle(
        "Merp", 25, "Yellow", 3.45
        );

    turtle.printTurtle();
    //System.out.println(turtle.getAge());
    //System.out.println(turtle.getColor());
    //turtle.setColor("Blue");
    
    transparentTurtle.printTurtle();
    System.out.println(transparentTurtle.getAgeFromSubclass());
    System.out.println(transparentTurtle.getColorFromSubclass());
    //transparentTurtle.setColorThroughSubclass("Blue");
  }
}
