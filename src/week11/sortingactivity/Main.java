package week11.sortingactivity;

import java.util.Arrays;

public class Main{
  public static void main(String[] args) {
    int[] unsortedArray = Sorting.getArray(
        (args.length > 0) ? Integer.parseInt(args[0]) : Sorting.DEFAULT_ARRAY_LENGTH
        );
    System.out.println(Arrays.toString(unsortedArray));

    int[] sortedArray = Sorting.sortArray(unsortedArray);
    System.out.println(Arrays.toString(sortedArray));

  }
}
