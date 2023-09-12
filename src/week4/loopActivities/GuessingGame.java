package week4.loopActivities;

import java.util.Scanner;

public class GuessingGame {

    private static final Scanner scanner = new Scanner(System.in);

    private static final int minimumAnswer = 1;
    private static final int maximumAnswer = 100;

    public static void main(String[] args) {
        int answer = (int) (Math.random() * (maximumAnswer - minimumAnswer)) + minimumAnswer;
        int guesses = 0;

        while(true){
            System.out.printf("Guess a number from %d to %d or type q to quit. \n", minimumAnswer, maximumAnswer);

            String input = scanner.nextLine();

            if (input.equals("q")){
                System.out.println("Quitting... The answer was " + answer);
                return;
            }

            guesses++;

            int numericalInput = Integer.parseInt(input);

            if (numericalInput == answer){
                System.out.println("You're correct!");
                System.out.println("Guesses: " + guesses);
                return;
            }
            else if (numericalInput < answer){
                System.out.println("Too small.");
            }
            //Could again be replaced by else, but done this way for readability.
            else if (numericalInput > answer){
                System.out.println("Too large.");
            }
        }
    }
}
