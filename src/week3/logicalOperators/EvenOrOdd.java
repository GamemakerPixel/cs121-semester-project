package week3.logicalOperators;

import java.util.Scanner;

public class EvenOrOdd {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Enter an integer: ");
        int number = Integer.parseInt(scanner.nextLine());

        if (number % 2 == 0){
            System.out.println("This integer is even.");
        }
        else {
            System.out.println("This integer is odd.");
        }
    }
}
