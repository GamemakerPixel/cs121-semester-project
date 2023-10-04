package experiemental;

import java.util.concurrent.Callable;

public class CallableTest{
  public static void main(String[] args) throws Exception{
    sandwich(() -> { return "Eggs"; });
    System.out.println(" - - - - ");
    sandwich(() -> { return cheese(); });
  }

  private static void sandwich(Callable<String> callable) throws Exception{
    System.out.println("Bread");
    System.out.println(callable.call());
    System.out.println("Bread");

  }

  private static String cheese(){
    return "Cheese";
  }
}
