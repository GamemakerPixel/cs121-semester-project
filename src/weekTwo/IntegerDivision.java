package weekTwo;

import java.util.Scanner;

public class IntegerDivision {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int integer1 = 5;
        int integer2 = 3;

        System.out.println(integer1 / integer2);
        System.out.println((double) integer1 / integer2);
        System.out.println(integer1 / (double) integer2);
        System.out.println((double) (integer1 / integer2));
        System.out.println((int) ((double) integer1 / integer2));

        System.out.println("Enter a decimal");
        double decimal = Double.parseDouble(scanner.nextLine());
        System.out.println(decimal);
    }
}
