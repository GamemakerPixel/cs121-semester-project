package week3.logicalOperators;

import java.util.Scanner;

public class Triangles {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Define the lengths for three sides of a triangle.");
        double sideA = Double.parseDouble(scanner.nextLine());
        double sideB = Double.parseDouble(scanner.nextLine());
        double sideC = Double.parseDouble(scanner.nextLine());

        if (sideA == sideB && sideB == sideC) {
            System.out.println("This is an equilateral triangle.");
        }
        else if(sideA == sideB || sideB == sideC || sideC == sideA){
            System.out.println("This is an isosceles triangle.");
        }
        else{
            System.out.println("This is a scalene triangle.");
        }
    }
}
