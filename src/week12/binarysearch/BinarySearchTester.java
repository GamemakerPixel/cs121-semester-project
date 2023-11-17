package week12.binarysearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BinarySearchTester{
  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
    ArrayList<Integer> unsortedList = new ArrayList<>(
        Arrays.asList(5, 4, 6, 6, 9, 8, 3, 4, 5, 5, 2, 3, 4, 10, 0, 2, -3)
        );

    System.out.println("Unsorted: " + unsortedList);

    ArrayList<Integer> sortedList = BinarySearchDemo.quicksort(unsortedList);
    
    System.out.println("Sorted: " + sortedList);

    System.out.println("\nEnter an integer to search for:");
    int key = Integer.parseInt(scanner.nextLine());

    int index = BinarySearchDemo.binarySearch(sortedList, key);

    System.out.println(
        (index == -1) ? "Item not found." : String.format(
          "Item found at index %d, position %d.", index, index + 1
          )
        );
  }
}
