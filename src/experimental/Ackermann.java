package experimental;

import java.util.Scanner;
import java.util.HashMap;
import java.util.Vector;

public class Ackermann{
  public static final boolean MEMOIZATION_ENABLED = true;

  private static Scanner scanner = new Scanner(System.in);
  private static HashMap<Vector, Integer> memoization = new HashMap<>();

  private static int steps = 0;
  
  public static void main(String[] args) {
    System.out.println("Enter m:");
    int m = Integer.parseInt(scanner.nextLine());
    System.out.println("Enter n:");
    int n = Integer.parseInt(scanner.nextLine());

    System.out.println(ackermann(m, n));
  }

  public static int ackermann(int m, int n){
    steps++;

    Vector inputVector = new Vector(2);
    if (MEMOIZATION_ENABLED){
      inputVector.add(m);
      inputVector.add(n);

      if (memoization.containsKey(inputVector)){
        return memoization.get(inputVector);
      }
    }

    int returnValue;

    if (m == 0){
      returnValue = n + 1;
    }
    else if (n == 0){
      returnValue = ackermann(m - 1, 1);
    }
    else{
      returnValue = ackermann(m - 1, ackermann(m, n - 1));
    } 

    if (MEMOIZATION_ENABLED){
      memoization.put(inputVector, returnValue);
    }

    return returnValue;
  }
}
