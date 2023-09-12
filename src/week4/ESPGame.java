package week4;

import java.util.Scanner;

public class ESPGame {

    private static final int roundCount = 10;
    private static final String[] colors = {
            "red",
            "green",
            "blue",
            "orange",
            "yellow",
    };

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int score = 0;
        for (int roundIndex = 0; roundIndex < roundCount; roundIndex++){
            if (playRound()){
                score++;
            }
        }

        System.out.printf("Your score: %d / %d", score, roundCount);
    }

    protected static boolean playRound(){
        int randomColorIndex = (int) (Math.random() * colors.length);

        String randomColor = colors[randomColorIndex];

        System.out.println("What color did the computer choose?");
        String userColor = scanner.nextLine();

        System.out.println("The computer choose " + randomColor);

        return randomColor.equals(userColor);
    }
}
