package week16.jsonActivity;

import java.io.FileWriter;
import java.io.IOException;
import com.google.code.gson.Gson;

public class StudentWriter{
  public static void main(String[] args) {
    Student student = new Student("Turtle", 386547, 4.1);

    Gson gson = new Gson();

    try{
      FileWriter writer = new FileWriter("student.json");

      String jsonString = gson.toGson(student);

      writer.write(jsonString);
      writer.close();
    }
    catch (IOException exception){
      exception.printStackTrace();
    }
  }
}
