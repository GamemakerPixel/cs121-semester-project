package week5.fileactivity;

import java.util.ArrayList;
import java.io.IOException;
import java.io.FileWriter;

public class FileAppend extends FileWrite{
  public static void main(String[] args) {
    ArrayList<String> headers = promptForHeaders();

    ArrayList<ArrayList<String>> data = promptForData(headers);

    writeToFile(headers, data);

    System.out.println("Running append");
  }

  protected static void writeToFile(ArrayList<String> headers, ArrayList<ArrayList<String>> data){
    try{
      FileWriter writer = new FileWriter("append-file.txt", true);

      writer.write(formatData(headers, data));
      writer.close();
    }
    catch (IOException exception){
      exception.printStackTrace();
    }
  }
}
