package week11.sortingactivity;

import java.util.Scanner;

public class Sorting{
  public static final ARRAY_LENGTH = 5;

  private static final SCANNER = new Scanner(System.in);

  public static int[] getArray(){
    int[] array = new int[ARRAY_LENGTH];

    for (int i = 0; i < array.length; i++){
      System.out.println("Enter a number:");
      array[i] = Integer.parseInt(SCANNER.nextLine());
    }

    return array;
  } 
}
