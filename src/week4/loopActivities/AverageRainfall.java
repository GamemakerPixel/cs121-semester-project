package week4.loopActivities;

import java.util.Scanner;

public class AverageRainfall {

    private static final String[] monthNames = {
            "January",
            "February",
            "March",
            "April",
            "May",
            "June",
            "July",
            "August",
            "September",
            "October",
            "November",
            "December",
    };

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int totalRainfall = 0;

        System.out.println("How many years are in the data?");
        int yearCount = Integer.parseInt(scanner.nextLine());

        for (int yearIndex = 0; yearIndex < yearCount; yearIndex++){
            System.out.printf("- - - Rainfall in year %d - - -", yearIndex + 1);

            for (String monthName : monthNames) {
                System.out.printf("\nInches of rainfall during %s: ", monthName);
                totalRainfall += Integer.parseInt(scanner.nextLine());
            }
        }

        //Summarize
        System.out.println("\n\n- - - Data Summary - - -");
        System.out.printf("Total rainfall over %d years: %d\n", yearCount, totalRainfall);
        System.out.printf("Average rainfall over %d years: %.2f\n",
                yearCount,
                (double) totalRainfall / (yearCount * monthNames.length));
    }
}
