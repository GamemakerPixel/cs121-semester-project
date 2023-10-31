package week9;

import java.util.Scanner;

public class ArrayFromInput{

  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
    String[] titles;
    int[] seatingCounts;
    float[] ratings;

    System.out.println("How many movies to do want to record?");
    int movieCount = Integer.parseInt(scanner.nextLine());

    titles = new String[movieCount];
    seatingCounts = new int[movieCount];
    ratings = new float[movieCount];

    for (int movieIndex = 0; movieIndex < movieCount; movieIndex++){
      System.out.println("\nTitle:");
      titles[movieIndex] = scanner.nextLine();

      System.out.println("Seating Count:");
      seatingCounts[movieIndex] = Integer.parseInt(scanner.nextLine());

      System.out.println("Rating:");
      ratings[movieIndex] = Float.parseFloat(scanner.nextLine());
    }

    System.out.printf("%-20s %-20s %s",
        "Title", "Seating Count", "Rating\n");

    for (int movieIndex = 0; movieIndex < movieCount; movieIndex++){
      System.out.printf("%-20s %-20s %.2f\n",
          titles[movieIndex], 
          seatingCounts[movieIndex], 
          ratings[movieIndex]);
    }
  }
}
