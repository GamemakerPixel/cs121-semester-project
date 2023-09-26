package week5;

import java.util.Scanner;

public class MethodsActivity{

  private static Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
    double length = getLength();
    double width = getWidth();
    double area = getArea(length, width);

    displayData(length, width, area);
  }

  private static double getLength(){
    System.out.println("Enter the length of the rectangle:");

    return Double.parseDouble(scanner.nextLine());
  }

  private static double getWidth(){
    System.out.println("Enter the width of the rectangle:");

    return Double.parseDouble(scanner.nextLine());
  }

  private static double getArea(double length, double width){
    return length * width;
  }

  private static void displayData(double length, double width, double area){
    System.out.printf("Length: %.2f \n" + 
        "Width: %.2f \n" +
        "Area: %.2f \n",
        length, width, area);
  }
}
