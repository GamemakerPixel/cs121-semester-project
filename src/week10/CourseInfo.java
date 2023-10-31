package week10;

import java.util.ArrayList;

public class CourseInfo{
  private static ArrayList<String> names;
  private static ArrayList<Double> gpas;
  private static ArrayList<Integer> scores;
  private static ArrayList<Character> letterGrades;

  public CourseInfo(){
    names = new ArrayList<>();
    gpas = new ArrayList<>();
    scores = new ArrayList<>();
    letterGrades = new ArrayList<>();
  }

  public void addCourse(String name, double gpa, int score, char letterGrade){
    names.add(name);
    gpas.add(gpa);
    scores.add(score);
    letterGrades.add(letterGrade);
  }

  @Override
  public String toString(){
    String string = "";

    string += String.format("%-20s %-5s %-5s %-12s\n",
        "Name",
        "GPA",
        "Score",
        "Grade");

    for (int index = 0; index < names.size(); index++){
      string += String.format("%-20s %-5.2f %-5d %-12c\n",
          names.get(index),
          gpas.get(index),
          scores.get(index),
          letterGrades.get(index)
          );
    }

    return string;
  }

}
