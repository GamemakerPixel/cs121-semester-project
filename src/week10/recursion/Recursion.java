package week10.recursion;



public class Recursion{
  public static void countDown(int start){
    if (start == 0){
      System.out.println("Blast Off!");
      return;
    }

    System.out.println(start + "...");
    countDown(start - 1);
  }

  public static void alphaBackwards(char start){
    System.out.println(start + " ");

    if (start != 'a'){
      alphaBackwards((char) (start - 1));
    }
  }
}
