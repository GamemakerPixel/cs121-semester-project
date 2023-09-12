package week4.loopActivities;

import java.util.Scanner;

public class TestAverage {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("How many students are there?");

        int studentCount = Integer.parseInt(scanner.nextLine());

        System.out.println("How many tests per student are there?");

        int testCount = Integer.parseInt(scanner.nextLine());

        for (int studentIndex = 0; studentIndex < studentCount; studentIndex++){
            int[] scores = new int[testCount];

            System.out.println("- - - - Student " + (studentIndex + 1) + " - - - -");
            for (int testIndex = 0; testIndex < testCount; testIndex++){
                System.out.printf("Test %d score: ", testIndex + 1);
                scores[testIndex] = Integer.parseInt(scanner.nextLine());
            }

            System.out.printf("The average for student %d is %.2f \n\n", studentIndex + 1, average(scores));
        }
    }

    private static double average(int[] integerArray){
        int total = 0;

        for (int integer: integerArray){
            total += integer;
        }

        return (double) total / integerArray.length;
    }
}
