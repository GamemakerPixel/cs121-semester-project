package week10;

import java.util.Scanner;

public class CourseInfoTester{
  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
    CourseInfo courseInfo = new CourseInfo();

    while (true){
      System.out.println("Enter a course name (or \"q\" to quit):");
      String name = scanner.nextLine();

      if (name.equals("q")){ break; }

      System.out.println("Enter a gpa:");
      double gpa = Double.parseDouble(scanner.nextLine());

      System.out.println("Enter a score:");
      int score = Integer.parseInt(scanner.nextLine());

      System.out.println("Enter a letter grade:");
      char letterGrade = scanner.nextLine().charAt(0);

      courseInfo.addCourse(name, gpa, score, letterGrade);
    }

    System.out.println(courseInfo);
  }
}
