package week16.jsonActivity;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import com.google.gson.Gson;

public class StudentWriter{
  public static void main(String[] args) {
    Student student = new Student("Turtle", 386547, 4.1);

    Gson gson = new Gson();

    try{
      File directory = new File("data/week16/jsonActivity");
      directory.mkdirs();

      FileWriter writer = new FileWriter("data/week16/jsonActivity/student.json");

      String jsonString = gson.toJson(student);

      writer.write(jsonString);
      writer.close();
    }
    catch (IOException exception){
      exception.printStackTrace();
    }
  }
}
