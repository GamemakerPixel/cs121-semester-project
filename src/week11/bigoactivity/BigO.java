package week11.bigoactivity;



public class BigO{
  public static void printOnce(int n){
    System.out.println(n);
  }

  public static void printNTimes(int n){
    System.out.print("\n");
    while (n > 0){
      System.out.print(n + " ");
      n--;
    }
  }

  // Still O(n^2), runs according to f(x) = (1/2)x^2 + (1/2)x + 0, can also think of scaling area of a triangle
  public static void printNSquaredTimes(int n){
    System.out.print("\n"); // May change the formula slightly, but still O(x^2)
    for (int i = n; i > 0; i--){
      System.out.print(i + " ");
    }

    if (n - 1 > 0){
      printNSquaredTimes(n - 1);
    }
  }
}
