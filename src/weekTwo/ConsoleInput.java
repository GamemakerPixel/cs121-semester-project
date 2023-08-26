package weekTwo;

import java.util.Scanner;

public class ConsoleInput {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your first name:");
        String firstName = scanner.nextLine();
        System.out.println("Enter your last name:");
        String lastName = scanner.nextLine();
        System.out.println("Pick a number:");
        int number = Integer.parseInt(scanner.nextLine());

        System.out.printf("Your full name is %s %s and you picked %d \n", firstName, lastName, number);
    }
}
