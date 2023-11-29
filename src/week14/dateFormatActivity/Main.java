package week14.dateFormatActivity;

import java.util.Scanner;



public class Main{
  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
    System.out.println("Enter the number of Calories consumed per day for this diet:");
    int caloriesPerDay = Integer.parseInt(scanner.nextLine());

    System.out.println("Enter the start date of the diet (mm/dd/yy):");
    String startDateString = scanner.nextLine();

    System.out.println("Enter the end date of the diet (mm/dd/yy):");
    String endDateString = scanner.nextLine();
    
    TotalCalories.DietInfo dietInfo = TotalCalories.calculateDietInfo(caloriesPerDay, startDateString, endDateString);
    
    if (dietInfo == null){ return; } // Exception occured

    System.out.printf("This diet consumes %d Calories per day, for %d days, resulting in a total of %d " +
        "Calories being consumed.\n",
        dietInfo.getCaloriesPerDay(),
        dietInfo.getTotalDays(),
        dietInfo.getTotalCalories()
        );
  }
}
