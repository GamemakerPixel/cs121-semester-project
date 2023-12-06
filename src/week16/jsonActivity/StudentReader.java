package week16.jsonActivity;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class StudentReader{
  public static void main(String[] args) {
    Gson gson = new Gson();

    try{
      BufferedReader reader = new BufferedReader(new FileReader("data/week16/jsonActivity/student.json"));

      Student student = gson.fromJson(reader, Student.class);

      System.out.println(student);
    }
    catch (IOException exception){
      System.out.println("Could not find student.json!");
    }
  }
}
