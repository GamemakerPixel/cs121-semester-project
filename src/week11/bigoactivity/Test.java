package week11.bigoactivity;



public class Test{
  public static void main(String[] args) {
    // Requirements suggest instancing the object, but it has no
    // instance variables so it makes more sense using static methods.
    
    BigO.printOnce(10);
    System.out.print("\n");
    BigO.printNTimes(10);
    System.out.print("\n");
    BigO.printNSquaredTimes(10);

  }
}
