package weekTwo;

import java.util.Scanner;

public class MathMethods {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Provide some numbers please.");
        System.out.print("x (int): ");
        int x = Integer.parseInt(scanner.nextLine());
        System.out.print("y (int): ");
        int y = Integer.parseInt(scanner.nextLine());
        System.out.print("z (double): ");
        double z = Double.parseDouble(scanner.nextLine());

        System.out.printf("Your numbers are as follows: \n" +
                "x: %d\ty: %d\tz: %.2f\n\n",
                x, y, z);

        System.out.println("Max of x and y: " + Math.max(x, y));
        System.out.println("Min of x and y: " + Math.min(x, y));
        System.out.println("Square root of x: " + Math.sqrt(x));
        System.out.println("x to the power of y: " + Math.pow(x, y));
        System.out.println("Ceiling of z: " + Math.ceil(z));
        System.out.println("Floor of z: " + Math.floor(z));
        System.out.println("z rounded: " + Math.round(z));
        System.out.println("Absolute value of x: " + Math.abs(x));

    }
}
