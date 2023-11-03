package week11.sortingactivity;

import java.util.Arrays;

public class Main{
  public static void main(String[] args) {
    int[] unsortedArray = Sorting.getArray();
    System.out.println(Arrays.asList(unsortedArray));

    int[] sortedArray = Storing.sortArray(unsortedArray);
    System.out.println(Arrays.asList(sortedArray));

  }
}
