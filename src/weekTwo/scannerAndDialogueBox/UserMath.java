package weekTwo.scannerAndDialogueBox;

import java.util.Scanner;

public class UserMath {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Enter a value for x: ");
        double x = Double.parseDouble(scanner.nextLine());
        System.out.print("Enter a value for y: ");
        double y = Double.parseDouble(scanner.nextLine());

        System.out.printf(
                "Here are a few operations with those numbers: \n" +
                        "x + y = %.2f \n" +
                        "x - y = %.2f \n" +
                        "x * y = %.2f \n" +
                        "x / y = %.2f \n" +
                        "sqrt(x) = %.2f \n" +
                        "x ^ y = %.2f \n" +
                        "ln(x) = %.2f \n" +
                        "log(x) = %.2f \n",
                x + y,
                x - y,
                x * y,
                x / y,
                Math.sqrt(x),
                Math.pow(x, y),
                Math.log(x),
                Math.log10(x)
                );
    }
}
